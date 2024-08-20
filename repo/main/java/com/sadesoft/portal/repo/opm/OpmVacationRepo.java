package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.filter.VacationFilter;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpmVacationRepo extends JdbcRepo<Vacation> implements VacationRepo {
    public OpmVacationRepo(Connection connection) {
        super(connection, "opm_hrm_vacation", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("description", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("total_days", true),
                new JdbcColumn("uuid"),
                new JdbcColumn("vacation_end_date", true),
                new JdbcColumn("vacation_start_date", true),
                new JdbcColumn("vacation_status", true),
                new JdbcColumn("vacation_time"),
                new JdbcColumn("vacation_type_id", true)));
    }

    @Override
    protected Vacation toObject(ResultSet s) throws SQLException {
            return new Vacation(
                getId(s, "id"),
                getId(s, "employee_id"),
                getId(s, "vacation_type_id"),
                getLocalDateTime(s, "vacation_start_date"),
                getLocalDateTime(s, "vacation_end_date"),
                s.getInt("total_days"),
                s.getString("description"),
                s.getString("vacation_status"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Vacation o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.description);
        setId(s, i++, o.employeeId);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 0);
        s.setInt(i++, o.dayCount);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        setLocalDateTime(s, i++, o.endTime);
        setLocalDateTime(s, i++, o.startTime);
        s.setString(i++, o.status);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);
        setId(s, i++, o.categoryId);
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Vacation> listBy(VacationFilter filter) {
        return call(() -> query(createStatement(createQuerySql("", tableName + " v", createWhereSql(filter)))));
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
            "v.*, c.type_name category_name, v.vacation_type_id category_id," +
            "e.name first_name, e.surname last_name",
            "opm_hrm_vacation v " +
                    "JOIN opm_hrm_vacation_type c ON v.vacation_type_id = c.id " +
                    "JOIN opm_hrm_employee e ON v.employee_id = e.id",
                    whereSql);
    }

    private String createWhereSql(VacationFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.employeeId != null) conditions.add("v.employee_id = " + f.employeeId);
        if (f.status != null) conditions.add("v.vacation_status = '" + f.status + "'");
        return String.join(" and ", conditions);
    }
}
