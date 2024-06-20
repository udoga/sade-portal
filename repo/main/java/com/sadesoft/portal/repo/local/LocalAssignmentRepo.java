package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    protected void fillStatement(PreparedStatement s, Assignment o) throws SQLException {
        int i = 1;
        s.setString(i++, o.userId);
        s.setString(i++, o.roleId);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public Assignment deleteByValues(Assignment a) {
        return super.deleteByValues(a);
    }
}
