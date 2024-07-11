package com.sadesoft.portal.base.repo;

import com.sadesoft.portal.base.model.Props;

import java.io.FileNotFoundException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConnector {
    public final String url;
    public final String username;
    public final String password;
    public final String driver;
    public final String initPath;

    public JdbcConnector(Props props) {
        this.url = props.getString("url", "");
        this.username = props.getString("username", "");
        this.password = props.getString("password", "");
        this.driver = props.getString("driver", "");
        this.initPath = props.getString("init_path", "");
    }

    public Connection connect() throws Exception {
        if (!driver.isEmpty()) Class.forName(driver);
        Connection c = DriverManager.getConnection(url, username, password);
        if (!initPath.isEmpty()) initialize(c);
        return c;
    }

    private void initialize(Connection c) throws Exception {
        InputStream s = JdbcConnector.class.getResourceAsStream("/" + initPath);
        if (s == null) throw new FileNotFoundException("Resource not found: " + initPath);
        c.createStatement().execute(new String(s.readAllBytes()));
    }
}
