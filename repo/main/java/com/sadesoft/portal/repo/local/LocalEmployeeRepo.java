package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.filter.EmployeeFilter;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.EmployeeRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalEmployeeRepo extends JdbcRepo<Employee> implements EmployeeRepo {
    public LocalEmployeeRepo(Connection connection) {
        super(connection, "employees", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id", true),
                new JdbcColumn("first_name", true),
                new JdbcColumn("last_name", true)));
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
    protected PreparedStatement fillStatement(PreparedStatement s, Employee o) throws SQLException {
        int i = 1;
        setId(s, i++, o.userId);
        s.setString(i++, o.firstName);
        s.setString(i++, o.lastName);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Employee> listBy(EmployeeFilter filter) {
        return call(() -> query(createStatement(createQuerySql("", tableName + " e", createWhereSql(filter)))));
    }

    @Override
    public List<DetailedEmployee> listDetailedBy(EmployeeFilter filter) {
        String sql = createDetailedQuerySql(createWhereSql(filter));
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee));
    }

    @Override
    public DetailedEmployee readDetailed(String id) {
        String sql = createDetailedQuerySql("e.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee)).get(0);
    }

    @Override
    public List<Employee> listSummarized() {
        String sql = getQuerySql("id, first_name, last_name") + getOrderSql("first_name");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedEmployee));
    }

    private Employee toSummarizedEmployee(ResultSet s) throws SQLException {
        return new Employee().withId(getId(s, "id"))
                .withFirstName(s.getString("first_name"))
                .withLastName(s.getString("last_name"));
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

    private String createWhereSql(EmployeeFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.afterId != null) conditions.add("e.id > " + f.afterId);
        if (f.userId != null) conditions.add("e.user_id = " + f.userId);
        return String.join(" and ", conditions);
    }
}
