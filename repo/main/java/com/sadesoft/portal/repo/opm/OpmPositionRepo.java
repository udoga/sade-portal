package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.repo.PositionRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmPositionRepo extends JdbcRepo<Position> implements PositionRepo {
    public OpmPositionRepo(Connection connection) {
        super(connection, "opm_hrm_employee_position", "p", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("competencies"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("job_description"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("name", true),
                new JdbcColumn("organization_id"),
                new JdbcColumn("uuid")));
    }

    @Override
    protected Position toObject(ResultSet s) throws SQLException {
        return new Position(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Position o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.name);
        if (inserting) s.setInt(i++, 0);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Position> listOptions() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toPositionOption));
    }

    private Position toPositionOption(ResultSet s) throws SQLException {
        return new Position().withId(getId(s, "id")).withName(s.getString("name"));
    }
}
