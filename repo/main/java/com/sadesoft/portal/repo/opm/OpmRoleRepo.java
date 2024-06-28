package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmRoleRepo extends JdbcRepo<Role> implements RoleRepo {
    public OpmRoleRepo(Connection connection) {
        super(connection, "opm_sys_role", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name")));
    }

    @Override
    protected Role toObject(ResultSet s) throws SQLException {
        return new Role(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Role o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
    }
}
