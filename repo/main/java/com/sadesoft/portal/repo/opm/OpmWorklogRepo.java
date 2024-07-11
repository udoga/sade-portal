package com.sadesoft.portal.repo.opm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedWorklog;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.repo.WorklogRepo;

public class OpmWorklogRepo extends JdbcRepo<Worklog> implements WorklogRepo {
    public OpmWorklogRepo(Connection connection) {
        super(connection, "opm_ps_project_work_log", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("project_id"),
                new JdbcColumn("employee_id"),
                new JdbcColumn("activity_id"),
                new JdbcColumn("work_date"),
                new JdbcColumn("work_duration_in_minutes"),
                new JdbcColumn("worklog_status"),
                new JdbcColumn("work_content")));
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
    protected void fillStatement(PreparedStatement s, Worklog o) throws SQLException {
        int i = 1;
        setId(s, i++, o.projectId);
        setId(s, i++, o.employeeId);
        setId(s, i++, o.activityId);
        setLocalDate(s, i++, o.workDate);
        s.setInt(i++, o.duration);
        s.setString(i++, o.status);
        s.setString(i++, o.description);
        if (o.id != null) setId(s, i, o.id);
    }

    @Override
    public List<DetailedWorklog> listWithDetails() {
        String sql = createQuerySqlWithDetails();
        return call(() -> queryFor(createStatement(sql), this::toDetailedWorklog));
    }

    public DetailedWorklog toDetailedWorklog(ResultSet s) throws SQLException {
        return new DetailedWorklog(
                toObject(s),
                new Project(getId(s, "project_id"), null, s.getString("project_name"), null),
                new Employee(getId(s, "employee_id"), null, s.getString("first_name"), s.getString("last_name")),
                new Activity(getId(s, "project_id"), s.getString("activity_name")));
    }

    private String createQuerySqlWithDetails() {
        return createQuerySql(
                "w.*, p.name project_name, a.name activity_name, e.name first_name, e.surname last_name",
                "opm_ps_project_work_log w " +
                        "JOIN opm_ps_project p ON w.project_id = p.id " +
                        "JOIN opm_hrm_employee e ON w.employee_id = e.id " +
                        "JOIN opm_ps_activity a ON w.activity_id = a.id",
                "");
    }
}
