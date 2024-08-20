package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.repo.VacationCategoryRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class OpmVacationCategoryRepo extends JdbcRepo<VacationCategory> implements VacationCategoryRepo {
    public OpmVacationCategoryRepo(Connection connection) {
        super(connection, "opm_hrm_vacation_type", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("base_vacation_type"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("max_day"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("type_name", true),
                new JdbcColumn("uuid")));
    }

    @Override
    protected VacationCategory toObject(ResultSet s) throws SQLException {
        return new VacationCategory(
                getId(s, "id"),
                s.getString("type_name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, VacationCategory o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setString(i++, "");
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setNull(i++, java.sql.Types.BIGINT);
        if (inserting) s.setInt(i++, 0);
        s.setString(i++, o.name);
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<VacationCategory> listSummarized() {
        String sql = getQuerySql("id, type_name") + getOrderSql("type_name");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedVacationCategory));
    }

    private VacationCategory toSummarizedVacationCategory(ResultSet s) throws SQLException {
        return new VacationCategory().withId(getId(s, "id")).withName(s.getString("type_name"));
    }
}
