package com.sadesoft.portal.repo.jdbc;

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
import java.util.List;

public class JdbcAssignmentRepo extends JdbcRepo<Assignment> implements AssignmentRepo {
    private final String selectWithRoleSql;

    public JdbcAssignmentRepo(Connection c, String tableName, List<JdbcColumn> columns, String roleTableName) {
        super(c, "assignments", columns);
        selectWithRoleSql = "SELECT a.*, r.name role_name FROM " + tableName +
                " a JOIN " + roleTableName + " r ON a.role_id = r.id";
    }

    @Override
    public Assignment toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Assignment(
                getId(s, getColumnName(i++)),
                getId(s, getColumnName(i++)),
                getId(s, getColumnName(i)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Assignment o) throws SQLException {
        int i = 1;
        setId(s, i++, o.userId);
        setId(s, i++, o.roleId);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<DetailedAssignment> listWithRole() {
        return call(() -> queryFor(createStatement(selectWithRoleSql), this::toDetailedAssignment));
    }

    @Override
    public List<DetailedAssignment> listWithRoleByUserId(String userId) {
        String sql = selectWithRoleSql + " where user_id = " + userId;
        return call(() -> queryFor(createStatement(sql), this::toDetailedAssignment));
    }

    public DetailedAssignment toDetailedAssignment(ResultSet s) throws SQLException {
        return new DetailedAssignment(
                toObject(s),
                null,
                new Role(getId(s, "role_id"), s.getString("role_name")));
    }
}
