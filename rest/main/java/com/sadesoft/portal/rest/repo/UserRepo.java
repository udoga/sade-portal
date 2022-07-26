package com.sadesoft.portal.rest.repo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.User;

public class UserRepo extends JdbcRepo<User> {
    public UserRepo(Connection connection) {
        super(connection, "users", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("username"),
                new JdbcColumn("full_name")));
    }

    @Override
    public User toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new User(
                s.getInt(getColumnName(i++)),
                s.getString(getColumnName(i++)),
                s.getString(getColumnName(i)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, User u) throws SQLException {
        int i = 1;
        s.setString(i++, u.username);
        s.setString(i++, u.fullName);
        if (u.id != null) s.setInt(i, u.id);
    }
}
