package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedClient;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Sector;
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
        super(connection, "opm_ps_client", "c", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("code"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("name", true),
                new JdbcColumn("organization_id"),
                new JdbcColumn("sector", true),
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
    protected PreparedStatement fillStatement(PreparedStatement s, Client o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.name);
        if (inserting) s.setInt(i++, 0);
        setId(s, i++, o.sectorId);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Client> listSummarized() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedClient));
    }

    @Override
    public List<DetailedClient> listDetailed() {
        String sql = getDetailedQuerySql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedClient));
    }

    @Override
    public DetailedClient readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("c.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedClient)).get(0);
    }

    private DetailedClient toDetailedClient(ResultSet s) throws SQLException {
        return new DetailedClient(
            toObject(s),
            new Sector(getId(s, "sector"), s.getString("sector_name")));
    }

    private Client toSummarizedClient(ResultSet s) throws SQLException {
        return new Client().withId(getId(s, "id")).withName(s.getString("name"));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "c.*, s.name sector_name",
            tableName + " " + tableAlias +
                " join opm_ps_client_sector s on c.sector = s.id");
    }
}
