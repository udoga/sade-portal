package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.repo.GrantRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalGrantRepo extends JdbcRepo<Grant> implements GrantRepo {
    public LocalGrantRepo(Connection connection) {
        super(connection, "grants", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("role_id", true),
                new JdbcColumn("permission_id", true)));
    }

    @Override
    protected Grant toObject(ResultSet s) throws SQLException {
        return new Grant(
                getId(s, "id"),
                getId(s, "role_id"),
                getId(s, "permission_id"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Grant o) throws SQLException {
        int i = 1;
        setId(s, i++, o.roleId);
        setId(s, i++, o.permissionId);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }
}
