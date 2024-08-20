package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.repo.SessionRepo;

public class LocalSessionRepo extends JdbcRepo<Session> implements SessionRepo {
    public LocalSessionRepo(Connection connection) {
        super(connection, "sessions", "s", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id", true),
                new JdbcColumn("begin_time", true),
                new JdbcColumn("end_time", true),
                new JdbcColumn("token", true)));
    }

    @Override
    public Session toObject(ResultSet s) throws SQLException {
        return new Session(
                getId(s, "id"),
                getId(s, "user_id"),
                getLocalDateTime(s, "begin_time"),
                getLocalDateTime(s, "end_time"),
                s.getString("token"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, Session o) throws SQLException {
        int i = 1;
        setId(s, i++, o.userId);
        setLocalDateTime(s, i++, o.beginTime);
        setLocalDateTime(s, i++, o.endTime);
        s.setString(i++, o.token);
        if (o.id != null) setId(s, i, o.id);
        return s;
    }
}
