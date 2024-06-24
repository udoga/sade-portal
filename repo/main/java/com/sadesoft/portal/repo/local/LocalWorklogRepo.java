package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;

public class LocalWorklogRepo extends JdbcRepo<Worklog> implements WorklogRepo {
    public LocalWorklogRepo(Connection connection) {
        super(connection, "worklogs", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("type_id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("project_id"),
                new JdbcColumn("work_date"),
                new JdbcColumn("duration"),
                new JdbcColumn("status"),
                new JdbcColumn("description")));
    }


    @Override
    public Worklog toObject(ResultSet s) throws SQLException {
        return new Worklog(
                getId(s, "id"),
                getId(s, "type_id"),
                getId(s, "employee_id"),
                getId(s, "project_id"),
                getLocalDate(s, "work_date"),
                s.getInt("duration"),
                s.getString("status"),
                s.getString("description")
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Worklog o) throws SQLException {
        int i = 1;
        setId(s, i++, o.typeId);
        setId(s, i++, o.employeeId);
        setId(s, i++, o.projectId);
        setLocalDate(s, i++, o.workDate);
        s.setInt(i++, o.duration);
        s.setString(i++, o.status);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
    }
}
