package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.SectorRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmSectorRepo extends JdbcRepo<Sector> implements SectorRepo {
    public OpmSectorRepo(Connection connection) {
        super(connection, "opm_ps_client_sector", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("name"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("uuid")));
    }

    @Override
    protected Sector toObject(ResultSet s) throws SQLException {
        return new Sector(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Sector o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setInt(i++, 0);
        setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.name);
        s.setInt(i++, 0);
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Sector> listSummarized() {
        String sql = createQuerySql("id, name", "", "", "name", "");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedSector));
    }

    private Sector toSummarizedSector(ResultSet s) throws SQLException {
        return new Sector().withId(getId(s, "id")).withName(s.getString("name"));
    }
}
