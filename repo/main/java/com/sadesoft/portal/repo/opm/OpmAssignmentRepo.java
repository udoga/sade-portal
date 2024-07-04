package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.repo.jdbc.JdbcAssignmentRepo;

import java.sql.Connection;
import java.util.List;

public class OpmAssignmentRepo extends JdbcAssignmentRepo {
    public OpmAssignmentRepo(Connection c) {
        super(c, "opm_sys_user_role", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("user_id"),
                new JdbcColumn("role_id")), "opm_sys_role");
    }
}
