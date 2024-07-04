package com.sadesoft.portal.repo.jdbc;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.repo.ActivityRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcActivityRepo extends JdbcRepo<Activity> implements ActivityRepo {
    public JdbcActivityRepo(Connection c, String tableName, List<JdbcColumn> columns) {
        super(c, tableName, columns);
    }

    @Override
    protected Activity toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new Activity(
                getId(s, getColumnName(i++)),
                s.getString(getColumnName(i)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, Activity o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
    }
}
