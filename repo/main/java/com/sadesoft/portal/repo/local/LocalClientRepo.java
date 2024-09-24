package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedClient;
import com.sadesoft.portal.core.query.ClientQuery;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.ClientRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalClientRepo extends JdbcRepo<Client> implements ClientRepo {
    public LocalClientRepo(Connection connection) {
        super(connection, "clients", "c", List.of(
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
    public List<Client> listOptions() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toClientOption));
    }

    @Override
    public List<Client> listBy(ClientQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedClient> listDetailedBy(ClientQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
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
                new Sector(getId(s, "sector_id"), s.getString("sector_name")));
    }

    private Client toClientOption(ResultSet s) throws SQLException {
        return new Client().withId(getId(s, "id")).withName(s.getString("name"));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "c.*, s.name sector_name",
            tableName + " " + tableAlias +
                " left join sectors s on c.sector_id = s.id");
    }

    private String getWhereSql(ClientQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.searchName != null) conditions.add("POSITION('c.name', '" + q.searchName + "') > 0");
        return getWhereSql(String.join(" and ", conditions));
    }
}
