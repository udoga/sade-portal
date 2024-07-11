package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedVacation;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmVacationRepo extends JdbcRepo<Vacation> implements VacationRepo {
    public OpmVacationRepo(Connection connection) {
        super(connection, "opm_hrm_vacation", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("vacation_type_id"),
                new JdbcColumn("vacation_start_date"),
                new JdbcColumn("vacation_end_date"),
                new JdbcColumn("total_days"),
                new JdbcColumn("description"),
                new JdbcColumn("vacation_status")));
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
    protected void fillStatement(PreparedStatement s, Vacation o) throws SQLException {
        int i = 1;
        setId(s, i++, o.employeeId);
        setId(s, i++, o.categoryId);
        setLocalDateTime(s, i++, o.startTime);
        setLocalDateTime(s, i++, o.endTime);
        s.setInt(i++, o.dayCount);
        s.setString(i++, o.description);
        s.setString(i++, o.status);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<Vacation> listByStatus(String status) {
        return call(() -> query(createStatement(createQuerySql("", "", "status = '" + status + "'"))));
    }

    @Override
    public List<DetailedVacation> listDetailed() {
        String sql = createDetailedQuerySql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedVacation));
    }

    public DetailedVacation toDetailedVacation(ResultSet s) throws SQLException {
        return new DetailedVacation(
                toObject(s),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new VacationCategory(getId(s, "category_id"), s.getString("category_name")));
    }

    private String createDetailedQuerySql() {
        return createQuerySql(
                "v.*, c.type_name category_name, v.vacation_type_id category_id",
                "opm_hrm_vacation v " +
                        "JOIN opm_hrm_vacation_type c ON v.vacation_type_id = c.id " +
                        "JOIN opm_hrm_employee e ON v.employee_id = e.id",
                "");
    }
}
