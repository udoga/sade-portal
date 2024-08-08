package com.sadesoft.portal.repo.opm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.base.repo.JdbcRepo;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.repo.UserRepo;

public class OpmUserRepo extends JdbcRepo<User> implements UserRepo {
    public OpmUserRepo(Connection connection) {
        super(connection, "opm_sys_user", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("created_by"),
                new JdbcColumn("created_time"),
                new JdbcColumn("full_name"),
                new JdbcColumn("language_code"),
                new JdbcColumn("last_login_time"),
                new JdbcColumn("last_password_update_time"),
                new JdbcColumn("last_updated_by"),
                new JdbcColumn("last_updated_time"),
                new JdbcColumn("organization_id"),
                new JdbcColumn("password_hash_sha512"),
                new JdbcColumn("reset_token"),
                new JdbcColumn("security_password_hash_sha512"),
                new JdbcColumn("status"),
                new JdbcColumn("timezone"),
                new JdbcColumn("user_email"),
                new JdbcColumn("user_name"),
                new JdbcColumn("uuid")));
    }

    @Override
    public User toObject(ResultSet s) throws SQLException {
        return new User(
                getId(s, "id"),
                s.getString("user_name"),
                s.getString("password_hash_sha512"));
    }

    @Override
    protected void fillStatement(PreparedStatement s, User o) throws SQLException {
        int i = 1;
        boolean inserting = (o.id == null);
        if (inserting) s.setInt(i++, 0);
        if (inserting) setLocalDateTime(s, i++, LocalDateTime.now());
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setNull(i++, java.sql.Types.TIMESTAMP);
        s.setNull(i++, java.sql.Types.TIMESTAMP);
        s.setInt(i++, 0);
        setLocalDateTime(s, i++, LocalDateTime.now());
        s.setInt(i++, 0);
        s.setString(i++, o.password);
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setString(i++, "");
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setNull(i++, java.sql.Types.VARCHAR);
        s.setString(i++, o.username);
        s.setObject(i++, UUID.randomUUID());
        if (!inserting) setId(s, i, o.id);
    }

    @Override
    public List<User> listSummarized() {
        String sql = createQuerySql("id, user_name", "", "", "user_name", "");
        return call(() -> queryFor(createStatement(sql), this::toSummarizedUser));
    }

    private User toSummarizedUser(ResultSet s) throws SQLException {
        return new User().withId(getId(s, "id")).withUsername(s.getString("user_name"));
    }
}
