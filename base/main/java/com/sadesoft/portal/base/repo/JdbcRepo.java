package com.sadesoft.portal.base.repo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import com.sadesoft.portal.base.error.RecordNotFoundError;

public abstract class JdbcRepo<T extends Record> implements Repo<T> {
    protected final Connection connection;
    protected final String tableName;
    protected final List<JdbcColumn> columns;

    public JdbcRepo(Connection connection, String tableName, List<JdbcColumn> columns) {
        this.connection = connection;
        this.tableName = tableName;
        this.columns = columns;
    }

    @Override
    public List<T> list() {
        return call(() -> query(createSelectSql("")));
    }

    @Override
    public List<T> listPage(int pageNumber, int pageSize) {
        return call(() -> query(createSelectSql(" LIMIT " + (pageSize) + " OFFSET " + ((pageNumber-1) * pageSize))));
    }

    @Override
    public T create(T record) {
        return call(() -> command(createInsertSql(), record, true));
    }

    @Override
    public T read(int id) {
        return call(() -> getFirst(query(createSelectSql(" where " + getIdName() + " = " + id)), id));
    }

    @Override
    public T update(int id, T record) {
        return call(() -> command(createUpdateSql(), record, true));
    }

    @Override
    public T delete(int id) {
        return call(() -> command(createDeleteSql(id), read(id), false));
    }

    protected abstract T toObject(ResultSet s) throws SQLException;

    protected abstract void fillStatement(PreparedStatement s, T t)  throws SQLException;

    protected char getChar(ResultSet set, String columnName) throws SQLException {
        return set.getString(columnName).charAt(0);
    }

    protected void setChar(PreparedStatement s, int index, char c) throws SQLException {
        s.setString(index, String.valueOf(c));
    }

    protected LocalDate getLocalDate(ResultSet set, String columnName) throws SQLException {
        Date date = set.getDate(columnName);
        return (date == null)? null : date.toLocalDate();
    }

    protected void setLocalDate(PreparedStatement s, int index, LocalDate date) throws SQLException {
        s.setDate(index, (date == null)? null : java.sql.Date.valueOf(date));
    }

    private <V> V call(Callable<V> callable) {
        try { return callable.call(); }
        catch (RuntimeException e) { throw e; }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    private List<T> query(String sql) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet set = statement.executeQuery();
        List<T> objects = getObjects(set);
        statement.close();
        return objects;
    }

    private T command(String sql, T record, boolean fill) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql, getColumnNames().toArray(new String[0]));
        if (fill) fillStatement(statement, record);
        statement.executeUpdate();
        return fill? getFirst(getObjects(statement.getGeneratedKeys()), record.getId()) : record;
    }

    private T getFirst(List<T> objects, Integer id) {
        if (objects.isEmpty()) throw new RecordNotFoundError(tableName, id);
        return objects.get(0);
    }

    private List<T> getObjects(ResultSet s) throws SQLException {
        ArrayList<T> users = new ArrayList<>();
        while (s.next()) users.add(toObject(s));
        return users;
    }

    private List<String> getColumnNames() {
        List<String> result = new ArrayList<>();
        for (JdbcColumn column: columns) result.add(column.name);
        return result;
    }

    protected String getColumnName(int index) {
        return columns.get(index).name;
    }

    private String getIdName() {
        return getColumnName(0);
    }

    private String createSelectSql(String whereSql) {
        return "select " + repeat("%1$s", true) + " from " + tableName + whereSql;
    }

    private String createInsertSql() {
        return "insert into " + tableName + " (" + repeat("%1$s", false) + ") values (" + repeat("%2$s", false) + ")";
    }

    private String createUpdateSql() {
        return "update " + tableName + " set " + repeat("%1$s = %2$s", false) + " where " + getIdName() + " = ?";
    }

    private String createDeleteSql(Integer id) {
        return "delete from " + tableName + " where " + getIdName() + " = " + id.toString();
    }

    private String repeat(String template, boolean includeId) {
        String delimiter = ", ";
        StringBuilder result = new StringBuilder();
        for (JdbcColumn column: columns.subList(includeId? 0 : 1, columns.size()))
            result.append(String.format(template, column.name, getQuestionMark(column))).append(delimiter);
        return result.substring(0, result.length() - delimiter.length());
    }

    private String getQuestionMark(JdbcColumn column) {
        if (isPostgresql() && !column.type.isEmpty()) return "?::" + column.type;
        return "?";
    }

    private boolean isPostgresql() {
        try {
            return connection.getMetaData().getDriverName().toLowerCase().contains("postgresql");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
