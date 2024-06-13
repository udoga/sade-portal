package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
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
                new JdbcColumn("vacation_type", "vacation_type"),
                new JdbcColumn("replacement_employee_id"),
                new JdbcColumn("start_time"),
                new JdbcColumn("end_time"),
                new JdbcColumn("day_count"),
                new JdbcColumn("description"),
                new JdbcColumn("status", "approval_status")));
    }

    @Override
    protected Vacation toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Vacation(
                getId(s, getColumnName(i++)),
                getId(s, getColumnName(i++)),
                VacationType.valueOf(s.getString(getColumnName(i++))),
                getId(s, getColumnName(i++)),
                getLocalDateTime(s, getColumnName(i++)),
                getLocalDateTime(s, getColumnName(i++)),
                s.getInt(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                ApprovalStatus.valueOf(s.getString(getColumnName(i))));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Vacation l) throws SQLException {
        int i = 1;
        setId(s, i++, l.employeeId);
        s.setString(i++, l.vacationType.name());
        setId(s, i++, l.replacementEmployeeId);
        setLocalDateTime(s, i++, l.startTime);
        setLocalDateTime(s, i++, l.endTime);
        s.setInt(i++, l.dayCount);
        s.setString(i++, l.description);
        s.setString(i++, l.status.name());
        if (l.id != null) setId(s, i, l.id);
    }

    @Override
    public List<Vacation> paginateByStatus(int pageNo, int pageSize, ApprovalStatus status) {
        if (status == null) return paginate(pageNo, pageSize);
        return call(() -> query(createSelectSql(" WHERE " + " status = " + "'" + status + "'"
                + " LIMIT " + pageSize
                + " OFFSET " + ((pageNo-1) * pageSize))));
    }
}
