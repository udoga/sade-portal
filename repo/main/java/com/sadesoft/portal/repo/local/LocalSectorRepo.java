package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.repo.SectorRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalSectorRepo extends JdbcRepo<Sector> implements SectorRepo {
    public LocalSectorRepo(Connection connection) {
        super(connection, "sectors", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name", true)));
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
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Sector> listSummarized() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedSector));
    }

    private Sector toSummarizedSector(ResultSet s) throws SQLException {
        return new Sector().withId(getId(s, "id")).withName(s.getString("name"));
    }
}
