package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Vacation;
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
                new JdbcColumn("description")));
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
                null);
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
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<Vacation> paginateByStatus(int pageNo, int pageSize, ApprovalStatus status) {
        if (status == null) return paginate(pageNo, pageSize);
        return call(() -> query(createStatement(createSelectSql(" WHERE " + " status = " + "'" + status + "'"
                + " LIMIT " + pageSize
                + " OFFSET " + ((pageNo-1) * pageSize)))));
    }
}
