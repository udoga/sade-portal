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
        this.url = props.get("url");
        this.username = props.get("username");
        this.password = props.get("password");
        this.driver = props.get("driver");
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        if (!driver.isEmpty()) Class.forName(driver);
        return DriverManager.getConnection(url, username, password);
    }
}
