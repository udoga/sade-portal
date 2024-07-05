package com.sadesoft.portal.base.repo;

import java.util.List;

public class JdbcColumn {
    public String name;
    public String type;

    public JdbcColumn(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public JdbcColumn(String name) {
        this(name, "");
    }

    public boolean isUpdatable() {
        return !List.of("id", "created_by", "created_time").contains(name);
    }
}
