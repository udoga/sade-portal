package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedProject;
import com.sadesoft.portal.core.query.ProjectQuery;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.ProjectRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalProjectRepo extends JdbcRepo<Project> implements ProjectRepo {
    public LocalProjectRepo(Connection connection) {
        super(connection, "projects", "p", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("client_id", true),
                new JdbcColumn("name", true),
                new JdbcColumn("description", true)));
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
    protected PreparedStatement fillStatement(PreparedStatement s, Project o) throws SQLException {
        int i = 1;
        setId(s, i++, o.clientId);
        s.setString(i++, o.name);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Project> listBy(ProjectQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedProject> listDetailedBy(ProjectQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql()
                + getOffsetSql(q.offset);
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject));
    }

    @Override
    public List<Project> listOptions() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toProjectOption));
    }

    @Override
    public DetailedProject readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("p.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedProject)).get(0);
    }

    private Project toProjectOption(ResultSet s) throws SQLException {
        return new Project().withId(getId(s, "id")).withName(s.getString("name"));
    }

    public DetailedProject toDetailedProject(ResultSet s) throws SQLException {
        return new DetailedProject(
            toObject(s),
            new Client(getId(s, "client_id"), null, s.getString("client_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "p.*, c.name client_name",
            tableName + " " + tableAlias +
                " join clients c on p.client_id = c.id");
    }

    private String getWhereSql(ProjectQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.clientId != null) conditions.add("p.client_id = " + q.clientId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
