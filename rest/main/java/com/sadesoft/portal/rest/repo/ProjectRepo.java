package com.sadesoft.portal.rest.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Project;

public class ProjectRepo extends JdbcRepo<Project> {
    public ProjectRepo(Connection connection) {
        super(connection, "projects", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name")));
    }

    @Override
    public Project toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Project(
                getId(s, getColumnName(i++)),
                s.getString(getColumnName(i)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Project p) throws SQLException {
        int i = 1;
        s.setString(i++, p.name);
        if (p.id != null) setId(s, i, p.id);
    }
}
