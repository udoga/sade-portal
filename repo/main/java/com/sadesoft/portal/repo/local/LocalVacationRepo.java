package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.filter.VacationFilter;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.VacationRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalVacationRepo extends JdbcRepo<Vacation> implements VacationRepo {
    public LocalVacationRepo(Connection connection) {
        super(connection, "vacations", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("category_id", true),
                new JdbcColumn("start_time", true),
                new JdbcColumn("end_time", true),
                new JdbcColumn("day_count", true),
                new JdbcColumn("description", true),
                new JdbcColumn("status", true)));
    }

    @Override
    protected Vacation toObject(ResultSet s) throws SQLException {
        return new Vacation(
                getId(s, "id"),
                getId(s, "employee_id"),
                getId(s, "category_id"),
                getLocalDateTime(s, "start_time"),
                getLocalDateTime(s, "end_time"),
                s.getInt("day_count"),
                s.getString("description"),
                s.getString("status"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Vacation l) throws SQLException {
        int i = 1;
        setId(s, i++, l.employeeId);
        setId(s, i++, l.categoryId);
        setLocalDateTime(s, i++, l.startTime);
        setLocalDateTime(s, i++, l.endTime);
        s.setInt(i++, l.dayCount);
        s.setString(i++, l.description);
        s.setString(i++, l.status);
        if (l.id != null) setId(s, i, l.id);
        return s;
    }

    @Override
    public List<Vacation> listBy(VacationFilter filter) {
        return call(() -> query(createStatement(createQuerySql("", "", createWhereSql(filter)))));
    }

    @Override
    public List<DetailedVacation> listDetailedBy(VacationFilter filter) {
        String sql = createDetailedQuerySql(createWhereSql(filter));
        return call(() -> queryFor(createStatement(sql), this::toDetailedVacation));
    }

    @Override
    public DetailedVacation readDetailed(String id) {
        String sql = createDetailedQuerySql("v.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedVacation)).get(0);
    }

    public DetailedVacation toDetailedVacation(ResultSet s) throws SQLException {
        return new DetailedVacation(
                toObject(s),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new VacationCategory(getId(s, "category_id"), s.getString("category_name")));
    }

    private String createDetailedQuerySql(String whereSql) {
        return createQuerySql(
                "v.*, c.name category_name, e.first_name, e.last_name",
                "vacations v " +
                        "JOIN vacation_categories c ON v.category_id = c.id " +
                        "JOIN employees e ON v.employee_id = e.id",
                        whereSql);
    }

    private String createWhereSql(VacationFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.employeeId != null) conditions.add("v.employee_id = " + f.employeeId);
        if (f.status != null) conditions.add("v.status = '" + f.status + "'");
        return String.join(" and ", conditions);
    }
}
