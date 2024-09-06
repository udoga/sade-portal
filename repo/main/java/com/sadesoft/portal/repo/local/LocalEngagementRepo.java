package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedEngagement;
import com.sadesoft.portal.core.query.EngagementQuery;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.EngagementRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalEngagementRepo extends JdbcRepo<Engagement> implements EngagementRepo {
    public LocalEngagementRepo(Connection connection) {
        super(connection, "engagements", "e", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("project_id", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("position_id", true),
                new JdbcColumn("start_date", true),
                new JdbcColumn("end_date", true),
                new JdbcColumn("description", true),
                new JdbcColumn("status", true)));
    }

    @Override
    protected Engagement toObject(ResultSet s) throws SQLException {
        return new Engagement(
                getId(s, "id"),
                getId(s, "project_id"),
                getId(s, "employee_id"),
                getId(s, "position_id"),
                getLocalDate(s, "start_date"),
                getLocalDate(s, "end_date"),
                s.getString("description"),
                s.getString("status"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Engagement o) throws SQLException {
        int i = 1;
        setId(s, i++, o.projectId);
        setId(s, i++, o.employeeId);
        setId(s, i++, o.positionId);
        setLocalDate(s, i++, o.startDate);
        setLocalDate(s, i++, o.endDate);
        s.setString(i++, o.description);
        s.setString(i++, o.status);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Engagement> listBy(EngagementQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql();
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedEngagement> listDetailedBy(EngagementQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql("id") + getLimitSql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedEngagement));
    }

    private DetailedEngagement toDetailedEngagement(ResultSet s) throws SQLException {
        return new DetailedEngagement(
                toObject(s),
                new Project(getId(s, "project_id"), null, s.getString("project_name"), s.getString("project_desc")),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new Position(getId(s, "position_id"), s.getString("position_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "e.*, p.name project_name, p.description project_desc, m.first_name, m.last_name, o.name position_name",
            tableName + " " + tableAlias +
                " left join projects p on e.project_id = p.id " +
                " left join employees m on e.employee_id = m.id " +
                " left join positions o on e.position_id = o.id");
    }

    private String getWhereSql(EngagementQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.projectId != null) conditions.add("e.project_id = " + q.projectId);
        if (q.employeeId != null) conditions.add("e.employee_id = " + q.employeeId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
