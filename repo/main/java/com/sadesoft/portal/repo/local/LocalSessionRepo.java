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
        super(connection, "sessions", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id"),
                new JdbcColumn("begin_time"),
                new JdbcColumn("end_time"),
                new JdbcColumn("token")));
    }

    @Override
    public Session toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Session(
                getId(s, getColumnName(i++)),
                s.getString(getColumnName(i++)),
                getLocalDateTime(s, getColumnName(i++)),
                getLocalDateTime(s, getColumnName(i++)),
                s.getString(getColumnName(i)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Session o) throws SQLException {
        int i = 1;
        s.setString(i++, o.userId);
        setLocalDateTime(s, i++, o.beginTime);
        setLocalDateTime(s, i++, o.endTime);
        s.setString(i++, o.token);
        if (o.id != null) setId(s, i, o.id);
    }
}
