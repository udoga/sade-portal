package com.sadesoft.portal.repo.local;

import com.sadesoft.portal.base.repo.JdbcColumn;
import com.sadesoft.portal.repo.jdbc.JdbcActivityRepo;

import java.sql.Connection;
import java.util.List;

public class LocalActivityRepo extends JdbcActivityRepo {
    public LocalActivityRepo(Connection c) {
        super(c, "activities", List.of(
                new JdbcColumn("id"),
                new JdbcColumn("name")));
    }
}
