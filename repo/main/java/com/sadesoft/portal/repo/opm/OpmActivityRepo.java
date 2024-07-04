package com.sadesoft.portal.repo.opm;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.repo.jdbc.JdbcActivityRepo;

import java.sql.Connection;
import java.util.List;

public class OpmActivityRepo extends JdbcActivityRepo {
    public OpmActivityRepo(Connection c) {
        super(c, "opm_ps_activity", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name")));
    }
}
