package com.sadesoft.portal.base.repo;

import java.util.List;

public class JdbcColumn {
    public String name;

    public JdbcColumn(String name) {
        this.name = name;
    }

    public boolean isUpdatable() {
        return !List.of("id", "created_by", "created_time").contains(name);
    }
}
