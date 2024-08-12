package com.sadesoft.portal.repo.local;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class LocalUserRepo extends JdbcRepo<User> implements UserRepo {
    public LocalUserRepo(Connection connection) {
        super(connection, "users", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("username", true),
                new JdbcColumn("password", true)));
    }

    @Override
    public User toObject(ResultSet s) throws SQLException {
        return new User(
                getId(s, "id"),
                s.getString("username"),
                s.getString("password"));
    }

    @Override
    protected PreparedStatement fillStatement(PreparedStatement s, User u) throws SQLException {
        int i = 1;
        s.setString(i++, u.username);
        s.setString(i++, u.password);
        if (u.id != null) setId(s, i, u.id);
        return s;
    }

    @Override
    public List<User> listSummarized() {
        String sql = createQuerySql("id, username", "", "", "username", "");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedUser));
    }

    private User toSummarizedUser(ResultSet s) throws SQLException {
        return new User().withId(getId(s, "id")).withUsername(s.getString("username"));
    }
}
