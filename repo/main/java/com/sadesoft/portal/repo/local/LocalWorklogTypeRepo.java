package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.WorklogType;
import com.sadesoft.portal.core.repo.WorklogTypeRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LocalWorklogTypeRepo extends JdbcRepo<WorklogType> implements WorklogTypeRepo {
    public LocalWorklogTypeRepo(Connection connection) {
        super(connection, "worklog_types", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name")));
    }

    @Override
    protected WorklogType toObject(ResultSet s) throws SQLException {
        return new WorklogType(
                getId(s, "id"),
                s.getString("name"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, WorklogType o) throws SQLException {
        int i = 1;
        s.setString(i++, o.name);
        if (o.id != null) setId(s, i, o.id);
    }
}
