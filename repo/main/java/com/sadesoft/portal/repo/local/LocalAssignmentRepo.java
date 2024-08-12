package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.sql.*;
import java.util.List;

public class LocalAssignmentRepo extends JdbcRepo<Assignment> implements AssignmentRepo {
    public LocalAssignmentRepo(Connection connection) {
        super(connection, "assignments", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id"),
                new JdbcColumn("role_id")));
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
        setId(s, i++, o.userId);
        setId(s, i++, o.roleId);
        if (o.id != null) setId(s, i, o.id);
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
                "assignments a JOIN roles r ON a.role_id = r.id",
                whereSql);
    }

    private String createWhereSql(String userId) {
        return (userId == null)? "" : ("user_id = " + userId);
    }
}
