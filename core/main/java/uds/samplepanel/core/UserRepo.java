package uds.samplepanel.core;

import uds.samplepanel.base.DatabaseRepo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepo extends DatabaseRepo<User> {
    public UserRepo(Connection connection) {
        super(connection, "users", List.of("id", "username", "full_name"));
    }

    @Override
    public User toObject(ResultSet s) throws SQLException {
        int i = 0;
        return new User(
                s.getInt(columnNames.get(i++)),
                s.getString(columnNames.get(i++)),
                s.getString(columnNames.get(i)));
    }

    @Override
    protected void fillStatement(PreparedStatement s, User u) throws SQLException {
        int i = 1;
        s.setString(i++, u.username);
        s.setString(i++, u.fullName);
        if (u.id != null) s.setInt(i, u.id);
    }
}
