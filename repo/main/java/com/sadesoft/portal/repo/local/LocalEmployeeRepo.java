package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.EmployeeRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalEmployeeRepo extends JdbcRepo<Employee> implements EmployeeRepo {
    public LocalEmployeeRepo(Connection connection) {
        super(connection, "employees", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id"),
                new JdbcColumn("first_name"),
                new JdbcColumn("last_name")));
    }

    @Override
    protected Employee toObject(ResultSet s) throws SQLException {
        return new Employee(
                getId(s, "id"),
                getId(s, "user_id"),
                s.getString("first_name"),
                s.getString("last_name"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Employee o) throws SQLException {
        int i = 1;
        setId(s, i++, o.userId);
        s.setString(i++, o.firstName);
        s.setString(i++, o.lastName);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<Employee> listBy(String userId) {
        return call(() -> query(createStatement(createQuerySql("", "", createWhereSql(userId)))));
    }

    @Override
    public List<DetailedEmployee> listDetailedBy(String userId) {
        String sql = createDetailedQuerySql(createWhereSql(userId));
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee));
    }

    @Override
    public DetailedEmployee readDetailed(String id) {
        String sql = createDetailedQuerySql("id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee)).get(0);
    }

    private DetailedEmployee toDetailedEmployee(ResultSet s) throws SQLException {
        return new DetailedEmployee(
            toObject(s),
            new User(getId(s, "user_id"), s.getString("username"), null));
    }

    private String createDetailedQuerySql(String whereSql) {
        return createQuerySql(
            "e.*, u.username username",
            "employees e JOIN users u ON e.user_id = u.id",
            whereSql);
    }

    private String createWhereSql(String userId) {
        return (userId == null)? "" : ("user_id = " + userId);
    }
}
