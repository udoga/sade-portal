package com.sadesoft.portal.repo.opm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;

public class OpmWorklogRepo extends JdbcRepo<Worklog> implements WorklogRepo {
    public OpmWorklogRepo(Connection connection) {
        super(connection, "opm_ps_project_work_log", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("project_id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("activity_id"),
                new JdbcColumn("work_date"),
                new JdbcColumn("work_duration_in_minutes"),
                new JdbcColumn("worklog_status"),
                new JdbcColumn("work_content")));
    }


    @Override
    public Worklog toObject(ResultSet s) throws SQLException {
        return new Worklog(
                getId(s, "id"),
                getId(s, "project_id"),
                getId(s, "employee_id"),
                getId(s, "activity_id"),
                getLocalDate(s, "work_date"),
                s.getInt("work_duration_in_minutes"),
                s.getString("worklog_status"),
                s.getString("work_content")
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Worklog o) throws SQLException {
        int i = 1;
        setId(s, i++, o.projectId);
        setId(s, i++, o.employeeId);
        setId(s, i++, o.activityId);
        setLocalDate(s, i++, o.workDate);
        s.setInt(i++, o.duration);
        s.setString(i++, o.status);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
    }
}
