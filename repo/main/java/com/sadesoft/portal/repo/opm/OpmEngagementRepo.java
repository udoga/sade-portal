package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.detailed.DetailedEngagement;
import com.sadesoft.portal.core.filter.EngagementFilter;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.repo.EngagementRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

public class OpmEngagementRepo extends JdbcRepo<Engagement> implements EngagementRepo {
    public OpmEngagementRepo(Connection connection) {
        super(connection, "opm_ps_project_employee", "e", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("description", true),
                new JdbcColumn("employee_id", true),
                new JdbcColumn("end_date", true),
                new JdbcColumn("organization_id"),
                new JdbcColumn("position_id", true),
                new JdbcColumn("project_id", true),
                new JdbcColumn("start_date", true),
                new JdbcColumn("status", true),
                new JdbcColumn("uuid")));
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
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.description);
        setId(s, i++, o.employeeId);
        setLocalDate(s, i++, o.endDate);
        if (inserting) s.setInt(i++, 0);
        setId(s, i++, o.positionId);
        setId(s, i++, o.projectId);
        setLocalDate(s, i++, o.startDate);
        s.setString(i++, o.status);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Engagement> listBy(EngagementFilter f) {
        String sql = getQuerySql() + getWhereSql(f) + getOrderSql("id") + getLimitSql();
        return call(() -> query(createStatement(sql)));
    }

    @Override
    public List<DetailedEngagement> listDetailedBy(EngagementFilter f) {
        String sql = getDetailedQuerySql() + getWhereSql(f) + getOrderSql("id") + getLimitSql();
        return call(() -> queryFor(createStatement(sql), this::toDetailedEngagement));
    }

    public DetailedEngagement toDetailedEngagement(ResultSet s) throws SQLException {
        return new DetailedEngagement(
                toObject(s),
                new Project(getId(s, "project_id"), null, s.getString("project_name"), s.getString("project_desc")),
                new Employee(getId(s, "employee_id"), null, s.getString("name"), s.getString("surname")),
                new Position(getId(s, "position_id"), s.getString("position_name")));
    }

    private String getDetailedQuerySql() {
        return getQuerySql(
            "e.*, p.name project_name, p.description project_desc, m.name, m.surname, o.name position_name",
            tableName + " " + tableAlias +
                " join opm_ps_project p on e.project_id = p.id " +
                " join opm_hrm_employee m on e.employee_id = m.id " +
                " join opm_hrm_employee_position o on e.position_id = o.id");
    }

    private String getWhereSql(EngagementFilter f) {
        List<String> conditions = new ArrayList<>();
        if (f.projectId != null) conditions.add("e.project_id = " + f.projectId);
        if (f.employeeId != null) conditions.add("e.employee_id = " + f.employeeId);
        return getWhereSql(String.join(" and ", conditions));
    }
}
