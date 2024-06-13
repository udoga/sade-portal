package com.sadesoft.portal.repo.opm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class OpmUserRepo extends JdbcRepo<User> implements UserRepo {
    private final PreparedStatement updateStatement;

    public OpmUserRepo(Connection connection) {
        super(connection, "opm_sys_user", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_name"),
                new JdbcColumn("password_hash_sha512")));
        updateStatement = createStatementWithColumns("update " + tableName + " set user_name = ?, password_hash_sha512 = ? where id = ?");
    }

    @Override
    public User toObject(ResultSet s) throws SQLException {
        return new User(
                getId(s, "id"),
                s.getString("user_name"),
                s.getString("password_hash_sha512"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, User u) throws SQLException {
        int i = 1;
        s.setString(i++, u.username);
        s.setString(i++, u.password);
        if (u.id != null) setId(s, i, u.id);
    }

    @Override
    public User update(String id, User user) {
        user.setId(id);
        return call(() -> updateCredentials(user));
    }

    private User updateCredentials(User user) throws SQLException {
        updateStatement.setString(1, user.username);
        updateStatement.setString(2, user.password);
        setId(updateStatement, 3, user.id);
        updateStatement.executeUpdate();
        return getFirst(getObjects(updateStatement.getGeneratedKeys()), user.id);
    }
}
