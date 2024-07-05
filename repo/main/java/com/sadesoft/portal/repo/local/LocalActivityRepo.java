package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.repo.ActivityRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalActivityRepo extends JdbcRepo<Activity> implements ActivityRepo {
    public LocalActivityRepo(Connection connection) {
        super(connection, "activities", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name")));
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
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
    }
}
