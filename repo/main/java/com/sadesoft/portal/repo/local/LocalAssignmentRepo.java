package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.repo.jdbc.JdbcAssignmentRepo;

import java.sql.*;
import java.util.List;

public class LocalAssignmentRepo extends JdbcAssignmentRepo {
    public LocalAssignmentRepo(Connection connection) {
        super(connection, "assignments", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id"),
                new JdbcColumn("role_id")), "roles");
    }
}
