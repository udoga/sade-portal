package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.repo.PositionRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalPositionRepo extends JdbcRepo<Position> implements PositionRepo {
    public LocalPositionRepo(Connection connection) {
        super(connection, "positions", "p", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name", true)));
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
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }
}
