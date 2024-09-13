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
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;
import com.sadesoft.portal.core.order.WorklogOrder;

public class LocalWorklogRepo extends JdbcRepo<Worklog> implements WorklogRepo {
    public LocalWorklogRepo(Connection connection) {
        super(connection, "worklogs", "w", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("project_id", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("activity_id", true),
                new JdbcColumn("work_date", true),
                new JdbcColumn("duration", true),
                new JdbcColumn("description", true),
                new JdbcColumn("status", true)));
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
                s.getString("description"),
                s.getString("status")
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
        s.setString(i++, o.description);
        s.setString(i++, o.status);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Worklog> listBy(WorklogQuery q) {
        String sql = getQuerySql() + getWhereSql(q) + getOrderSql(q.order) + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedWorklog> listDetailedBy(WorklogQuery q) {
        String sql = getDetailedQuerySql() + getWhereSql(q) + getOrderSql(q.order) + getLimitSql() + getOffsetSql(q.offset);
        return call(() -> queryFor(createStatement(sql), this::toDetailedWorklog));
    }

    @Override
    public DetailedWorklog readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("w.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedWorklog)).get(0);
    }

    public DetailedWorklog toDetailedWorklog(ResultSet s) throws SQLException {
        return new DetailedWorklog(
                toObject(s),
                new Project(getId(s, "project_id"), null, s.getString("project_name"), s.getString("description")),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new Activity(getId(s, "project_id"), s.getString("activity_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "w.*, p.name project_name, p.description, a.name activity_name, e.first_name, e.last_name",
            tableName + " " + tableAlias +
                " left join projects p on w.project_id = p.id" +
                " left join employees e on w.employee_id = e.id" +
                " left join activities a on w.activity_id = a.id");
    }

    private String getWhereSql(WorklogQuery q) {
        List<String> conditions = new ArrayList<>();
        if (q.projectId != null) conditions.add("w.project_id = " + q.projectId);
        if (q.employeeId != null) conditions.add("w.employee_id = " + q.employeeId);
        if (q.minDate != null) conditions.add("w.work_date >= '" + q.minDate + "'");
        if (q.maxDate != null) conditions.add("w.work_date <= '" + q.maxDate + "'");
        return getWhereSql(String.join(" and ", conditions));
    }

    private String getOrderSql(WorklogOrder o) {
        return getOrderSql(switch (o) {
            case ID -> "id";
            case NEWEST -> "work_date desc, id desc";
            case OLDEST -> "work_date, id";
        });
    }
}
