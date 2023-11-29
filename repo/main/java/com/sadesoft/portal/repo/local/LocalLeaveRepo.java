package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.repo.LeaveRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalLeaveRepo extends JdbcRepo<Leave> implements LeaveRepo {
    public LocalLeaveRepo(Connection connection) {
        super(connection, "leaves", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id"),
                new JdbcColumn("leave_type", "leave_type"),
                new JdbcColumn("replacement_user_id"),
                new JdbcColumn("start_time"),
                new JdbcColumn("end_time"),
                new JdbcColumn("day_count"),
                new JdbcColumn("description"),
                new JdbcColumn("status", "approval_status")));
    }

    @Override
    protected Leave toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Leave(
                getId(s, getColumnName(i++)),
                getId(s, getColumnName(i++)),
                LeaveType.valueOf(s.getString(getColumnName(i++))),
                getId(s, getColumnName(i++)),
                getLocalDateTime(s, getColumnName(i++)),
                getLocalDateTime(s, getColumnName(i++)),
                s.getInt(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                ApprovalStatus.valueOf(s.getString(getColumnName(i))));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Leave l) throws SQLException {
        int i = 1;
        setId(s, i++, l.userId);
        s.setString(i++, l.leaveType.name());
        setId(s, i++, l.replacementUserId);
        setLocalDateTime(s, i++, l.startTime);
        setLocalDateTime(s, i++, l.endTime);
        s.setInt(i++, l.dayCount);
        s.setString(i++, l.description);
        s.setString(i++, l.status.name());
        if (l.id != null) setId(s, i, l.id);
    }

    @Override
    public List<Leave> listFiltered(ApprovalStatus status, int pageNo, int pageSize) {
        if(status == null) return listPage(pageNo, pageSize);
        return call(() -> query(createSelectSql(" WHERE " + " status = " + "'"+ status.toString()+ "'"
                + " LIMIT " + (pageSize)
                + " OFFSET " + ((pageNo-1) * pageSize))));}
}
