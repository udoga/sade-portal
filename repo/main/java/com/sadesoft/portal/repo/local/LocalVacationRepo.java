package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.VacationRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalVacationRepo extends JdbcRepo<Vacation> implements VacationRepo {
    public LocalVacationRepo(Connection connection) {
        super(connection, "vacations", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("category_id"),
                new JdbcColumn("start_time"),
                new JdbcColumn("end_time"),
                new JdbcColumn("day_count"),
                new JdbcColumn("description"),
                new JdbcColumn("status")));
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
    protected void fillStatement(PreparedStatement s, Vacation l) throws SQLException {
        int i = 1;
        setId(s, i++, l.employeeId);
        setId(s, i++, l.categoryId);
        setLocalDateTime(s, i++, l.startTime);
        setLocalDateTime(s, i++, l.endTime);
        s.setInt(i++, l.dayCount);
        s.setString(i++, l.description);
        s.setString(i++, l.status);
        if (l.id != null) setId(s, i, l.id);
    }

    @Override
    public List<Vacation> listByStatus(String status) {
        return call(() -> query(createStatement(createQuerySql("", "", "status = '" + status + "'"))));
    }

    @Override
    public List<DetailedVacation> listWithDetails() {
        String sql = createQuerySqlWithDetails();
        return call(() -> queryFor(createStatement(sql), this::toDetailedVacation));
    }

    public DetailedVacation toDetailedVacation(ResultSet s) throws SQLException {
        return new DetailedVacation(
                toObject(s),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new VacationCategory(getId(s, "category_id"), s.getString("category_name")));
    }

    private String createQuerySqlWithDetails() {
        return createQuerySql(
                "v.*, c.name category_name, e.first_name, e.last_name",
                "vacations v " +
                        "JOIN vacation_categories c ON v.category_id = c.id " +
                        "JOIN employees e ON v.employee_id = e.id",
                "");
    }
}
