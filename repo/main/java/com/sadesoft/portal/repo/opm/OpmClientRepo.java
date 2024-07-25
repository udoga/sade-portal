package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.repo.ClientRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmClientRepo extends JdbcRepo<Client> implements ClientRepo {
    public OpmClientRepo(Connection connection) {
        super(connection, "opm_ps_client", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("code"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("name"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("sector"),
                new JdbcColumn("uuid")));
    }

    @Override
    protected Client toObject(ResultSet s) throws SQLException {
        return new Client(
                getId(s, "id"),
                getId(s, "sector"),
                s.getString("name"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Client o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        s.setString(i++, o.name);
        if (inserting) s.setInt(i++, 1);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setInt(i++, 1);
        setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.name);
        s.setInt(i++, 1);
        setId(s, i++, o.sectorId);
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
    }
}
