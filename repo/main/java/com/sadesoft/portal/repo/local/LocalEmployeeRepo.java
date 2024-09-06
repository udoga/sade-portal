package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedEmployee;
import com.sadesoft.portal.core.query.EmployeeQuery;
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
        super(connection, "employees", "e", List.of(
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
    public List<Employee> listBy(EmployeeQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedEmployee> listDetailedBy(EmployeeQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee));
    }

    @Override
    public DetailedEmployee readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("e.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedEmployee)).get(0);
    }

    @Override
    public List<Employee> listOptions() {
        String sql = getQuerySql("id, first_name, last_name") + getOrderSql("first_name");
        return call(() -> queryFor(createStatement(sql), this::toEmployeeOption));
    }

    private Employee toEmployeeOption(ResultSet s) throws SQLException {
        return new Employee().withId(getId(s, "id"))
                .withFirstName(s.getString("first_name"))
                .withLastName(s.getString("last_name"));
    }

    private DetailedEmployee toDetailedEmployee(ResultSet s) throws SQLException {
        return new DetailedEmployee(
            toObject(s),
            new User(getId(s, "user_id"), s.getString("username"), null));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "e.*, u.username username",
            tableName + " " + tableAlias +
                " join users u on e.user_id = u.id");
    }

    private String getWhereSql(EmployeeQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.userId != null) conditions.add("e.user_id = " + q.userId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
