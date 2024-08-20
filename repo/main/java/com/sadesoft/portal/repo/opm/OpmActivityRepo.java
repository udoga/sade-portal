package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.repo.ActivityRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static com.sadesoft.portal.base.util.StringUtil.convertTurkishToAscii;

public class OpmActivityRepo extends JdbcRepo<Activity> implements ActivityRepo {
    public OpmActivityRepo(Connection connection) {
        super(connection, "opm_ps_activity", "a", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("code", true),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("description"),
                new JdbcColumn("end_date"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("name", true),
                new JdbcColumn("organization_id"),
                new JdbcColumn("start_date"),
                new JdbcColumn("status"),
                new JdbcColumn("uuid")));
    }

    @Override
    protected Activity toObject(ResultSet s) throws SQLException {
        return new Activity(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Activity o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        s.setString(i++, convertTurkishToAscii(o.name));
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        if (inserting) s.setString(i++, "");
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.name);
        if (inserting) s.setInt(i++, 0);
        if (inserting) s.setNull(i++, java.sql.Types.TIMESTAMP);
        if (inserting) s.setString(i++, "");
        if (inserting) s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
        return s;
    }

    @Override
    public List<Activity> listSummarized() {
        String sql = getQuerySql("id, name") + getOrderSql("name");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedActivity));
    }

    private Activity toSummarizedActivity(ResultSet s) throws SQLException {
        return new Activity().withId(getId(s, "id")).withName(s.getString("name"));
    }
}
