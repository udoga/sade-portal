package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalProjectRepo extends JdbcRepo<Project> implements ProjectRepo {
    public LocalProjectRepo(Connection connection) {
        super(connection, "projects", List.of(
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

    @Override
    public List<Project> listBy(String clientId) {
        return call(() -> query(createStatement(createQuerySql("", tableName + " p", createWhereSql(clientId)))));
    }

    @Override
    public List<DetailedProject> listDetailedBy(String clientId) {
        String sql = createDetailedQuerySql(createWhereSql(clientId));
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject));
    }

    @Override
    public DetailedProject readDetailed(String id) {
        String sql = createDetailedQuerySql("p.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject)).get(0);
    }

    @Override
    public List<Project> listSummarized() {
        String sql = createQuerySql("id, name", "", "", "name", "");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedProject));
    }

    private Project toSummarizedProject(ResultSet s) throws SQLException {
        return new Project().withId(getId(s, "id")).withName(s.getString("name"));
    }

    public DetailedProject toDetailedProject(ResultSet s) throws SQLException {
        return new DetailedProject(
            toObject(s),
            new Client(getId(s, "client_id"), null, s.getString("client_name")));
    }

    private String createDetailedQuerySql(String whereSql) {
        return createQuerySql(
            "p.*, c.name client_name",
            "projects p JOIN clients c ON p.client_id = c.id",
            whereSql);
    }

    private String createWhereSql(String clientId) {
        return (clientId == null)? "" : ("p.client_id = " + clientId);
    }
}
