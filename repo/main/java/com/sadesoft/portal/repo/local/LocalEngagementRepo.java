package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.repo.EngagementRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalEngagementRepo extends JdbcRepo<Engagement> implements EngagementRepo {
    public LocalEngagementRepo(Connection connection) {
        super(connection, "engagements", "e", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("project_id", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("position_id", true),
                new JdbcColumn("start_date", true),
                new JdbcColumn("end_date", true),
                new JdbcColumn("description", true),
                new JdbcColumn("status", true)));
    }

    @Override
    protected Engagement toObject(ResultSet s) throws SQLException {
        return new Engagement(
                getId(s, "id"),
                getId(s, "project_id"),
                getId(s, "employee_id"),
                getId(s, "position_id"),
                getLocalDate(s, "start_date"),
                getLocalDate(s, "end_date"),
                s.getString("description"),
                s.getString("status"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Engagement o) throws SQLException {
        int i = 1;
        setId(s, i++, o.projectId);
        setId(s, i++, o.employeeId);
        setId(s, i++, o.positionId);
        setLocalDate(s, i++, o.startDate);
        setLocalDate(s, i++, o.endDate);
        s.setString(i++, o.description);
        s.setString(i++, o.status);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }
}
