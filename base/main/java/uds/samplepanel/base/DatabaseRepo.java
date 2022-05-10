package uds.samplepanel.base;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public abstract class DatabaseRepo<T extends Record> implements Repo<T> {
    protected final Connection connection;
    protected final String tableName;
    protected final List<String> columnNames;

    public DatabaseRepo(Connection connection, String tableName, List<String> columnNames) {
        this.connection = connection;
        this.tableName = tableName;
        this.columnNames = columnNames;
    }

    @Override
    public List<T> list() {
        return call(() -> query(createSelectSql("")));
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

    private <V> V call(Callable<V> callable) {
        try { return callable.call(); }
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
        PreparedStatement statement = connection.prepareStatement(sql, columnNames.toArray(new String[0]));
        if (fill) fillStatement(statement, record);
        statement.executeUpdate();
        return fill? getFirst(getObjects(statement.getGeneratedKeys()), record.id) : record;
    }

    private T getFirst(List<T> objects, Integer id) {
        if (objects.isEmpty()) throw new RecordNotFound(tableName, id);
        return objects.get(0);
    }

    private List<T> getObjects(ResultSet s) throws SQLException {
        ArrayList<T> users = new ArrayList<>();
        while (s.next()) users.add(toObject(s));
        return users;
    }

    private String getIdName() {
        return columnNames.get(0);
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
        for (String name: columnNames.subList(includeId? 0 : 1, columnNames.size()))
            result.append(String.format(template, name, "?")).append(delimiter);
        return result.substring(0, result.length() - delimiter.length());
    }
}
