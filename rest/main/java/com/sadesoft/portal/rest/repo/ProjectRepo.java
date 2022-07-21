package com.sadesoft.portal.rest.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Project;

public class ProjectRepo extends JdbcRepo<Project> {
    public ProjectRepo(Connection connection) {
        super(connection, "projects", List.of("id", "name"), List.of());
    }

    @Override
    public Project toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Project(
                s.getInt(columnNames.get(i++)),
                s.getString(columnNames.get(i)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Project p) throws SQLException {
        int i = 1;
        s.setString(i++, p.name);
        if (p.id != null) s.setInt(i, p.id);
    }
}
