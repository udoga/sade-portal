package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedGrant;
import com.sadesoft.portal.core.filter.GrantFilter;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.model.Permission;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.GrantRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OpmGrantRepo extends JdbcRepo<Grant> implements GrantRepo {
    public OpmGrantRepo(Connection connection) {
        super(connection, "opm_sys_role_permission", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("permission_id", true),
                new JdbcColumn("role_id", true),
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
    protected PreparedStatement fillStatement(PreparedStatement s, Grant o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 0);
        setId(s, i++, o.permissionId);
        setId(s, i++, o.roleId);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Grant> listBy(GrantFilter filter) {
        return call(() -> query(createStatement(createQuerySql("", "", createWhereSql(filter)))));
    }

    @Override
    public List<DetailedGrant> listDetailedBy(GrantFilter filter) {
        String sql = createDetailedQuerySql(createWhereSql(filter));
        return call(() -> queryFor(createStatement(sql), this::toDetailedGrant));
    }

    private DetailedGrant toDetailedGrant(ResultSet s) throws SQLException {
        return new DetailedGrant(
                toObject(s),
                new Role(getId(s, "role_id"), s.getString("role_name")),
                new Permission(getId(s, "permission_id"), s.getString("permission_name")));
    }

    private String createDetailedQuerySql(String whereSql) {
        return createQuerySql(
                "g.*, r.name role_name, p.code permission_name ",
                "opm_sys_role_permission g " +
                "JOIN opm_sys_role r ON g.role_id = r.id " +
                "JOIN opm_sys_permission p ON g.permission_id = p.id ",
                whereSql);
    }

    private String createWhereSql(GrantFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.roleId != null) conditions.add("g.role_id = " + f.roleId);
        if (f.permissionId != null) conditions.add("g.permission_id = " + f.permissionId);
        return String.join(" and ", conditions);
    }
}
