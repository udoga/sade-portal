package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.repo.EngagementRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.time.LocalDateTime;
import java.util.UUID;

public class OpmEngagementRepo extends JdbcRepo<Engagement> implements EngagementRepo {
    public OpmEngagementRepo(Connection connection) {
        super(connection, "opm_ps_project_employee", "e", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("description", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("end_date", true),
                new JdbcColumn("organization_id"),
                new JdbcColumn("position_id", true),
                new JdbcColumn("project_id", true),
                new JdbcColumn("start_date", true),
                new JdbcColumn("status", true),
                new JdbcColumn("uuid")));
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
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.description);
        setId(s, i++, o.employeeId);
        setLocalDate(s, i++, o.endDate);
        if (inserting) s.setInt(i++, 0);
        setId(s, i++, o.positionId);
        setId(s, i++, o.projectId);
        setLocalDate(s, i++, o.startDate);
        s.setString(i++, o.status);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }
}
