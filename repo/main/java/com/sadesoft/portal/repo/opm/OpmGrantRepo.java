package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.repo.GrantRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmGrantRepo extends JdbcRepo<Grant> implements GrantRepo {
    public OpmGrantRepo(Connection connection) {
        super(connection, "opm_sys_role_permission", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("permission_id"),
                new JdbcColumn("role_id"),
                new JdbcColumn("uuid")));
    }

    @Override
    public Grant toObject(ResultSet s) throws SQLException {
        return new Grant(
                getId(s, "id"),
                getId(s, "role_id"),
                getId(s, "permission_id"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Grant o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setInt(i++, 0);
        setId(s, i++, o.permissionId);
        setId(s, i++, o.roleId);
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
    }
}
