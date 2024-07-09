package com.sadesoft.portal.base.repo;

import com.sadesoft.portal.base.error.RecordNotFoundError;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

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
        String sql = createSelectSql("");
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public T create(T record) {
        String sql = createInsertSql();
        return call(() -> command(fill(createCommandStatement(sql), record)));
    }

    @Override
    public T read(String id) {
        String sql = createSelectSql(" where " + getIdName() + " = " + id);
        return call(() -> getOrThrow(query(createStatement(sql)), getIdName() + "=" + id));
    }

    @Override
    public T update(String id, T record) {
        record.setId(id);
        return call(() -> command(fill(createCommandStatement(createUpdateSql()), record)));
    }

    @Override
    public T delete(String id) {
        T result = read(id);
        call(() -> command(createStatement(createDeleteSql(id))));
        return result;
    }

    protected abstract T toObject(ResultSet s) throws SQLException;

    protected abstract void fillStatement(PreparedStatement s, T t)  throws SQLException;

    protected String getId(ResultSet set, String columnName) throws SQLException {
        return String.valueOf(set.getInt(columnName));
    }

    protected void setId(PreparedStatement s, int index, String id) throws SQLException {
        s.setInt(index, Integer.parseInt(id));
    }

    protected LocalDate getLocalDate(ResultSet set, String columnName) throws SQLException {
        Date date = set.getDate(columnName);
        return (date == null)? null : date.toLocalDate();
    }

    protected void setLocalDate(PreparedStatement s, int index, LocalDate date) throws SQLException {
        s.setDate(index, (date == null)? null : java.sql.Date.valueOf(date));
    }

    protected LocalDateTime getLocalDateTime(ResultSet set, String columnName) throws SQLException {
        Timestamp t = set.getTimestamp(columnName);
        return (t == null)? null : t.toLocalDateTime();
    }

    protected void setLocalDateTime(PreparedStatement s, int index, LocalDateTime time) throws SQLException {
        s.setTimestamp(index, (time == null)? null : java.sql.Timestamp.valueOf(time));
    }

    protected List<T> query(PreparedStatement statement) throws SQLException {
        return queryFor(statement, this::toObject);
    }

    protected <R> List<R> queryFor(PreparedStatement statement, ResultSetReader<R> reader) throws SQLException {
        ResultSet set = statement.executeQuery();
        List<R> objects = getObjects(set, reader);
        statement.close();
        return objects;
    }

    protected T command(PreparedStatement statement) throws SQLException {
        statement.executeUpdate();
        ResultSet set = statement.getGeneratedKeys();
        return set.isBeforeFirst()? getOrThrow(getObjects(set, this::toObject), "") : null;
    }

    protected PreparedStatement createStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql);
    }

    protected PreparedStatement createCommandStatement(String sql) throws SQLException {
        return connection.prepareStatement(sql, getColumnNames().toArray(new String[0]));
    }

    protected PreparedStatement fill(PreparedStatement s, T t) throws SQLException {
        fillStatement(s, t);
        return s;
    }

    protected T getOrThrow(List<T> objects, String message) {
        if (objects.isEmpty()) throw new RecordNotFoundError(tableName, message);
        return objects.get(0);
    }

    protected <R> List<R> getObjects(ResultSet s, ResultSetReader<R> reader) throws SQLException {
        ArrayList<R> objects = new ArrayList<>();
        while (s.next()) objects.add(reader.read(s));
        return objects;
    }

    protected List<String> getColumnNames() {
        List<String> result = new ArrayList<>();
        for (JdbcColumn column: columns) result.add(column.name);
        return result;
    }

    protected String getColumnName(int index) {
        return columns.get(index).name;
    }

    protected String getIdName() {
        return getColumnName(0);
    }

    protected String createSelectSql(String appendedSQL) {
        return "select " + repeat("%1$s", columns) + " from " + tableName + appendedSQL;
    }

    protected String createInsertSql() {
        List<JdbcColumn> list = columns.subList(1, columns.size());
        return "insert into " + tableName + " (" + repeat("%1$s", list) + ") values (" + repeat("%2$s", list) + ")";
    }

    protected String createUpdateSql() {
        List<JdbcColumn> list = columns.stream().filter(JdbcColumn::isUpdatable).toList();
        return "update " + tableName + " set " + repeat("%1$s = %2$s", list) + " where " + getIdName() + " = ?";
    }

    protected String createDeleteSql(String id) {
        return "delete from " + tableName + " where " + getIdName() + " = " + id;
    }

    protected String repeat(String template, List<JdbcColumn> columnList) {
        String delimiter = ", ";
        StringBuilder result = new StringBuilder();
        for (JdbcColumn c: columnList)
            result.append(String.format(template, c.name, getPlaceholder(c))).append(delimiter);
        return result.substring(0, result.length() - delimiter.length());
    }

    protected String getPlaceholder(JdbcColumn column) {
        if (isPostgresql() && !column.type.isEmpty()) return "?::" + column.type;
        return "?";
    }

    protected boolean isPostgresql() {
        return call(() -> connection.getMetaData().getDriverName().toLowerCase().contains("postgresql"));
    }

    protected <V> V call(Callable<V> callable) {
        try { return callable.call(); }
        catch (RuntimeException e) { throw e; }
        catch (Exception e) { throw new RuntimeException(e); }
    }

    @FunctionalInterface
    public interface ResultSetReader<T> {
        T read(ResultSet s) throws SQLException;
    }
}
