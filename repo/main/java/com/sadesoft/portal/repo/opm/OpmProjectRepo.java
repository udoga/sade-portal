package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OpmProjectRepo extends JdbcRepo<Project> implements ProjectRepo {
    public OpmProjectRepo(Connection connection) {
        super(connection, "opm_ps_project", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("client_id"),
                new JdbcColumn("name"),
                new JdbcColumn("description")));
    }

    @Override
    protected Project toObject(ResultSet s) throws SQLException {
        return new Project(
                getId(s, "id"),
                getId(s, "client_id"),
                s.getString("name"),
                s.getString("description")
        );
    }

    @Override
    protected void fillStatement(PreparedStatement s, Project o) throws SQLException {
        int i = 1;
        setId(s, i++, o.clientId);
        s.setString(i++, o.name);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
    }
}
