package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.repo.PermissionRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmPermissionRepo extends JdbcRepo<Permission> implements PermissionRepo {
    public OpmPermissionRepo(Connection connection) {
        super(connection, "opm_sys_permission", "p", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("code", true))
        );
    }

    @Override
    protected Permission toObject(ResultSet s) throws SQLException {
        return new Permission(
                getId(s, "id"),
                s.getString("code"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Permission o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Permission> listSummarized() {
        String sql = getQuerySql("id, code") + getOrderSql("code");
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<Permission> listByUserId(String userId) {
        String sql = getQuerySql("distinct p.id, p.code",
                tableName + " " + tableAlias +
                        " join opm_sys_role_permission g ON p.id = g.permission_id" +
                        " join opm_sys_role r ON g.role_id = r.id" +
                        " join opm_sys_user_role a ON r.id = a.role_id") +
                getWhereSql("a.user_id = " + userId);
        return call(() -> query(createStatement(sql)));
    }
}
