package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.RoleRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalRoleRepo extends JdbcRepo<Role> implements RoleRepo {
    public LocalRoleRepo(Connection connection) {
        super(connection, "roles", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name")));
    }

    @Override
    protected Role toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Role(
                getId(s, getColumnName(i++)),
                s.getString(getColumnName(i++)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Role o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<Role> listByUserId(String userId) {
        return call(() -> query("SELECT r.* FROM " + tableName +
                " r JOIN assignments ur ON r.id = ur.role_id WHERE ur.user_id = " + userId));
    }
}
