package com.sadesoft.portal.base.repo;

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
}
