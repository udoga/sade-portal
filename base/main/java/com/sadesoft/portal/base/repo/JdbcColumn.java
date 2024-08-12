package com.sadesoft.portal.base.repo;

public class JdbcColumn {
    public final String name;
    public final boolean updatable;

    public JdbcColumn(String name, boolean updatable) {
        this.name = name;
        this.updatable = updatable;
    }

    public JdbcColumn(String name) {
        this(name, false);
    }
}
