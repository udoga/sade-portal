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
        super(connection, "roles", "r", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name", true)));
    }

    @Override
    protected Role toObject(ResultSet s) throws SQLException {
        return new Role(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Role o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Role> listOptions() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toRoleOption));
    }

    private Role toRoleOption(ResultSet s) throws SQLException {
        return new Role().withId(getId(s, "id")).withName(s.getString("name"));
    }
}
