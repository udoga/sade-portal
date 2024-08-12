package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmAssignmentRepo extends JdbcRepo<Assignment> implements AssignmentRepo {
    public OpmAssignmentRepo(Connection connection) {
        super(connection, "opm_sys_user_role", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("role_id", true),
                new JdbcColumn("user_id", true),
                new JdbcColumn("uuid")));
    }

    @Override
    public Assignment toObject(ResultSet s) throws SQLException {
        return new Assignment(
                getId(s, "id"),
                getId(s, "user_id"),
                getId(s, "role_id"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Assignment o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 1);
        setId(s, i++, o.roleId);
        setId(s, i++, o.userId);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Assignment> listBy(String userId) {
        return call(() -> query(createStatement(createQuerySql("", "", createWhereSql(userId)))));
    }

    @Override
    public List<DetailedAssignment> listDetailedBy(String userId) {
        String sql = createDetailedQuerySql(createWhereSql(userId));
        return call(() -> queryFor(createStatement(sql), this::toDetailedAssignment));
    }

    private DetailedAssignment toDetailedAssignment(ResultSet s) throws SQLException {
        return new DetailedAssignment(
                toObject(s),
                null,
                new Role(getId(s, "role_id"), s.getString("role_name")));
    }

    private String createDetailedQuerySql(String whereSql) {
        return createQuerySql(
                "a.*, r.name role_name",
                "opm_sys_user_role a JOIN opm_sys_role r ON a.role_id = r.id",
                whereSql);
    }

    private String createWhereSql(String userId) {
        return (userId == null)? "" : ("user_id = " + userId);
    }
}
