package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.compound.AssignmentWithRole;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmAssignmentRepo extends JdbcRepo<Assignment> implements AssignmentRepo {
    private final String selectWithRoleSql =
            "SELECT a.*, r.name role_name FROM opm_sys_user_role a JOIN opm_sys_role r ON a.role_id = r.id";

    public OpmAssignmentRepo(Connection connection) {
        super(connection, "opm_sys_user_role", List.of(
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
    protected void fillStatement(PreparedStatement s, Assignment o) throws SQLException {
        int i = 1;
        s.setString(i++, o.userId);
        s.setString(i++, o.roleId);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<AssignmentWithRole> listWithRole() {
        return call(() -> queryFor(createStatement(selectWithRoleSql), this::toAssignmentWithRole));
    }

    @Override
    public List<AssignmentWithRole> listWithRoleByUserId(String userId) {
        String sql = selectWithRoleSql + " where user_id = " + userId;
        return call(() -> queryFor(createStatement(sql), this::toAssignmentWithRole));
    }

    public AssignmentWithRole toAssignmentWithRole(ResultSet s) throws SQLException {
        return new AssignmentWithRole(
                toObject(s),
                new Role(getId(s, "role_id"), s.getString("role_name")));
    }
}
