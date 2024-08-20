package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedAssignment;
import com.sadesoft.portal.core.filter.AssignmentFilter;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.AssignmentRepo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LocalAssignmentRepo extends JdbcRepo<Assignment> implements AssignmentRepo {
    public LocalAssignmentRepo(Connection connection) {
        super(connection, "assignments", "a", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id", true),
                new JdbcColumn("role_id", true)));
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
    public List<Assignment> listBy(AssignmentFilter f) {
        String sql = getQuerySql() + getWhereSql(f) + getLimitSql();
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedAssignment> listDetailedBy(AssignmentFilter f) {
        String sql = getDetailedQuerySql() + getWhereSql(f) + getLimitSql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedAssignment));
    }

    private DetailedAssignment toDetailedAssignment(ResultSet s) throws SQLException {
        return new DetailedAssignment(
                toObject(s),
                new User(getId(s, "user_id"), s.getString("username"), null),
                new Role(getId(s, "role_id"), s.getString("role_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "a.*, r.name role_name, u.username ",
            tableName + " " + tableAlias +
                " join users u on a.user_id = u.id" +
                " join roles r on a.role_id = r.id");
    }

    private String getWhereSql(AssignmentFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.userId != null) conditions.add("a.user_id = " + f.userId);
        if (f.roleId != null) conditions.add("a.role_id = " + f.roleId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
