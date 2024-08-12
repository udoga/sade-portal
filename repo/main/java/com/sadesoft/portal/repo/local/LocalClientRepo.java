package com.sadesoft.portal.repo.local;

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
import java.util.List;

public class LocalClientRepo extends JdbcRepo<Client> implements ClientRepo {
    public LocalClientRepo(Connection connection) {
        super(connection, "clients", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name", true),
                new JdbcColumn("sector_id", true)));
    }

    @Override
    protected Client toObject(ResultSet s) throws SQLException {
        return new Client(
                getId(s, "id"),
                getId(s, "sector_id"),
                s.getString("name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Client o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        setId(s, i++, o.sectorId);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Client> listSummarized() {
        String sql = createQuerySql("id, name", "", "", "name", "");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedClient));
    }

    private Client toSummarizedClient(ResultSet s) throws SQLException {
        return new Client().withId(getId(s, "id")).withName(s.getString("name"));
    }

    @Override
    public List<DetailedClient> listDetailed() {
        String sql = createDetailedQuerySql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedClient));
    }

    @Override
    public DetailedClient readDetailed(String id) {
        String sql = createDetailedQuerySqlWithId(id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedClient)).get(0);
    }

    public DetailedClient toDetailedClient(ResultSet s) throws SQLException {
        return new DetailedClient(
            toObject(s),
            new Sector(getId(s, "sector_id"), s.getString("sector_name")));
    }

    private String createDetailedQuerySql() {
        return createDetailedQuerySqlWithId("");
    }

    private String createDetailedQuerySqlWithId(String id) {
        return createQuerySql(
            "c.*, s.name sector_name",
            "clients c JOIN sectors s ON c.sector_id = s.id",
            id.isEmpty()? "" : (" c.id = " + id));
    }
}
