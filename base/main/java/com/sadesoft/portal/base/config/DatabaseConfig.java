package com.sadesoft.portal.base.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    public String url;
    public String username;
    public String password;
    public String driver;

    public DatabaseConfig(Props props) {
        this.url = props.getString("url");
        this.username = props.getString("username", "");
        this.password = props.getString("password", "");
        this.driver = props.getString("driver", "");
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        if (!driver.isEmpty()) Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
}
