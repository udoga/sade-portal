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
        super(connection, "opm_ps_activity", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("code"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("description"),
                new JdbcColumn("end_date"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("name"),
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
    protected void fillStatement(PreparedStatement s, Activity o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        s.setString(i++, convertTurkishToAscii(o.name));
        if (inserting) s.setInt(i++, 1);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.name);
        setLocalDateTime(s, i++, null);
        s.setInt(i++, 1);
        setLocalDateTime(s, i++, LocalDateTime.now());
        s.setString(i++, o.name);
        s.setInt(i++, 1);
        setLocalDateTime(s, i++, LocalDateTime.of(2022, 1, 1, 0, 0, 0));
        s.setString(i++, "A");
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
    }
}
