package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.filter.WorklogFilter;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;
import com.sadesoft.portal.core.order.WorklogOrder;

public class LocalWorklogRepo extends JdbcRepo<Worklog> implements WorklogRepo {
    public LocalWorklogRepo(Connection connection) {
        super(connection, "worklogs", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("project_id", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("activity_id", true),
                new JdbcColumn("work_date", true),
                new JdbcColumn("duration", true),
                new JdbcColumn("status", true),
                new JdbcColumn("description", true)));
    }

    @Override
    public Worklog toObject(ResultSet s) throws SQLException {
        return new Worklog(
                getId(s, "id"),
                getId(s, "project_id"),
                getId(s, "employee_id"),
                getId(s, "activity_id"),
                getLocalDate(s, "work_date"),
                s.getInt("duration"),
                s.getString("status"),
                s.getString("description")
        );
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Worklog o) throws SQLException {
        int i = 1;
        setId(s, i++, o.projectId);
        setId(s, i++, o.employeeId);
        setId(s, i++, o.activityId);
        setLocalDate(s, i++, o.workDate);
        s.setInt(i++, o.duration);
        s.setString(i++, o.status);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Worklog> listBy(WorklogFilter filter, WorklogOrder order) {
        String sql = createQuerySql("", tableName + " w", createWhereSql(filter), createOrderBySql(order));
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedWorklog> listDetailedBy(WorklogFilter filter, WorklogOrder order) {
        String sql = createDetailedQuerySql(createWhereSql(filter), createOrderBySql(order));
        return call(() -> queryFor(createStatement(sql), this::toDetailedWorklog));
    }

    @Override
    public DetailedWorklog readDetailed(String id) {
        String sql = createDetailedQuerySql("w.id = " + id, "");
        return call(() -> queryFor(createStatement(sql), this::toDetailedWorklog)).get(0);
    }

    public DetailedWorklog toDetailedWorklog(ResultSet s) throws SQLException {
        return new DetailedWorklog(
                toObject(s),
                new Project(getId(s, "project_id"), null, s.getString("project_name"), s.getString("description")),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new Activity(getId(s, "project_id"), s.getString("activity_name")));
    }

    private String createDetailedQuerySql(String whereSql, String orderBySql) {
        return createQuerySql(
            "w.*, p.name project_name, p.description, a.name activity_name, e.first_name, e.last_name",
            "worklogs w " +
                "JOIN projects p ON w.project_id = p.id " +
                "JOIN employees e ON w.employee_id = e.id " +
                "JOIN activities a ON w.activity_id = a.id",
            whereSql, orderBySql);
    }

    private String createWhereSql(WorklogFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.afterId != null) conditions.add("w.id > " + f.afterId);
        if (f.employeeId != null) conditions.add("w.employee_id = " + f.employeeId);
        return String.join(" and ", conditions);
    }

    private String createOrderBySql(WorklogOrder o) {
        return switch (o) {
            case ID -> "id";
            case NEWEST -> "work_date desc";
            case OLDEST -> "work_date";
        };
    }
}
