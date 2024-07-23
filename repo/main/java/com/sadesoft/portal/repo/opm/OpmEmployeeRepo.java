package com.sadesoft.portal.repo.opm;

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

public class OpmEmployeeRepo extends JdbcRepo<Employee> implements EmployeeRepo {
    public OpmEmployeeRepo(Connection connection) {
        super(connection, "opm_hrm_employee", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id"),
                new JdbcColumn("name"),
                new JdbcColumn("surname")));
    }

    @Override
    protected Employee toObject(ResultSet s) throws SQLException {
        return new Employee(
                getId(s, "id"),
                getId(s, "user_id"),
                s.getString("name"),
                s.getString("surname"));
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
            "e.*, u.user_name username",
            "opm_hrm_employee e JOIN opm_sys_user u ON e.user_id = u.id",
            whereSql);
    }

    private String createWhereSql(String userId) {
        return (userId == null)? "" : ("user_id = " + userId);
    }
}
