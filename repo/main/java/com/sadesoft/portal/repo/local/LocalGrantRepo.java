package com.sadesoft.portal.repo.local;

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
import java.util.ArrayList;
import java.util.List;

public class LocalGrantRepo extends JdbcRepo<Grant> implements GrantRepo {
    public LocalGrantRepo(Connection connection) {
        super(connection, "grants", "g", List.of(
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

    @Override
    public List<Grant> listBy(GrantFilter f) {
        String sql = getQuerySql() + getWhereSql(f) + getLimitSql() + getOffsetSql(f.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedGrant> listDetailedBy(GrantFilter f) {
        String sql = getDetailedQuerySql() + getWhereSql(f) + getLimitSql() + getOffsetSql(f.offset);
        return call(() -> queryFor(createStatement(sql), this::toDetailedGrant));
    }

    private DetailedGrant toDetailedGrant(ResultSet s) throws SQLException {
        return new DetailedGrant(
                toObject(s),
                new Role(getId(s, "role_id"), s.getString("role_name")),
                new Permission(getId(s, "permission_id"), s.getString("permission_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
                "g.*, r.name role_name, p.name permission_name ",
                tableName + " " + tableAlias +
                    " join roles r on g.role_id = r.id" +
                    " join permissions p on g.permission_id = p.id");
    }

    private String getWhereSql(GrantFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.roleId != null) conditions.add("g.role_id = " + f.roleId);
        if (f.permissionId != null) conditions.add("g.permission_id = " + f.permissionId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
