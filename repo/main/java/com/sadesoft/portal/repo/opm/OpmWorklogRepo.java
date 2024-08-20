package com.sadesoft.portal.repo.opm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.filter.WorklogFilter;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;
import com.sadesoft.portal.core.order.WorklogOrder;

public class OpmWorklogRepo extends JdbcRepo<Worklog> implements WorklogRepo {
    public OpmWorklogRepo(Connection connection) {
        super(connection, "opm_ps_project_work_log", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("activity_id", true),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("description"),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("project_id", true),
                new JdbcColumn("uuid"),
                new JdbcColumn("work_content", true),
                new JdbcColumn("work_date", true),
                new JdbcColumn("work_duration_in_minutes", true),
                new JdbcColumn("worklog_flag"),
                new JdbcColumn("worklog_status", true)));
    }

    @Override
    public Worklog toObject(ResultSet s) throws SQLException {
        return new Worklog(
                getId(s, "id"),
                getId(s, "project_id"),
                getId(s, "employee_id"),
                getId(s, "activity_id"),
                getLocalDate(s, "work_date"),
                s.getInt("work_duration_in_minutes"),
                s.getString("worklog_status"),
                s.getString("work_content")
        );
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Worklog o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        setId(s, i++, o.activityId);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setNull(i++, java.sql.Types.VARCHAR);
        setId(s, i++, o.employeeId);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 0);
        setId(s, i++, o.projectId);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        s.setString(i++, o.description);
        setLocalDate(s, i++, o.workDate);
        s.setInt(i++, o.duration);
        if (inserting) s.setNull(i++, java.sql.Types.BOOLEAN);
        s.setString(i++, o.status);
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Worklog> listBy(WorklogFilter f, WorklogOrder o) {
        String sql = getQuerySql() + getWhereSql(f) + getOrderSql(o) + getLimitSql() + getOffsetSql(f.offset);
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedWorklog> listDetailedBy(WorklogFilter f, WorklogOrder o) {
        String sql = getDetailedQuerySql() + getWhereSql(f) + getOrderSql(o) + getLimitSql() + getOffsetSql(f.offset);
        return call(() -> queryFor(createStatement(sql), this::toDetailedWorklog));
    }

    @Override
    public DetailedWorklog readDetailed(String id) {
        String sql = getDetailedQuerySql() + getWhereSql("w.id = " + id);
        return call(() -> queryFor(createStatement(sql), this::toDetailedWorklog)).get(0);
    }

    private DetailedWorklog toDetailedWorklog(ResultSet s) throws SQLException {
        return new DetailedWorklog(
                toObject(s),
                new Project(getId(s, "project_id"), null, s.getString("project_name"),
                        s.getString("project_description")),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new Activity(getId(s, "project_id"), s.getString("activity_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "w.*, p.name project_name, p.description project_description, " +
                 "a.name activity_name, " +
                 "e.name first_name, e.surname last_name",
            tableName + " " + tableAlias +
                " join opm_ps_project p on w.project_id = p.id" +
                " join opm_hrm_employee e on w.employee_id = e.id" +
                " join opm_ps_activity a on w.activity_id = a.id");
    }

    private String getWhereSql(WorklogFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.projectId != null) conditions.add("w.project_id = " + f.projectId);
        if (f.employeeId != null) conditions.add("w.employee_id = " + f.employeeId);
        return getWhereSql(String.join(" and ", conditions));
    }

    private String getOrderSql(WorklogOrder o) {
        return getOrderSql(switch (o) {
            case ID -> "id";
            case NEWEST -> "work_date desc";
            case OLDEST -> "work_date";
        });
    }
}
