package com.sadesoft.portal.base.repo;

import com.sadesoft.portal.base.model.Props;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcConfig {
    public String url;
    public String username;
    public String password;
    public String driver;
    public String initPath;

    public JdbcConfig(Props props) {
        this.url = props.getString("url", "");
        this.username = props.getString("username", "");
        this.password = props.getString("password", "");
        this.driver = props.getString("driver", "");
        this.initPath = props.getString("initPath", "");
    }

    public Connection connect() throws Exception {
        if (!driver.isEmpty())
            Class.forName(driver);
        return url.isEmpty() ? null : initialize(DriverManager.getConnection(url, username, password));
    }

    protected Connection initialize(Connection connection) throws Exception {
        if (initPath.isEmpty()) return connection;
        InputStream s = JdbcConfig.class.getResourceAsStream("/" + initPath);
        if (s == null) throw new Exception("Resource not found: " + initPath);
        connection.createStatement().execute(new String(s.readAllBytes()));
        return connection;
    }
}
