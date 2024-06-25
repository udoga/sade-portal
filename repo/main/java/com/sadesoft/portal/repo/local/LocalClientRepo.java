package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Client;
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
                new JdbcColumn("name")));
    }

    @Override
    protected Client toObject(ResultSet s) throws SQLException {
        return new Client(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Client o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
    }
}
