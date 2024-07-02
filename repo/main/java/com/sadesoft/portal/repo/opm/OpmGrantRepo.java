package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.repo.GrantRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmGrantRepo extends JdbcRepo<Grant> implements GrantRepo {
    public OpmGrantRepo(Connection connection) {
        super(connection, "opm_sys_role_permission", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("role_id"),
                new JdbcColumn("permission_id")));
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
        setId(s, i++, o.roleId);
        setId(s, i++, o.permissionId);
        if (o.id != null) setId(s, i, o.id);
    }
}
