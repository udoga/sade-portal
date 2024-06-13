package com.sadesoft.portal.base.repo;

import com.sadesoft.portal.base.model.Props;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcConfig {
    public String url;
    public String username;
    public String password;
    public String driver;

    public JdbcConfig(Props props) {
        this.url = props.getString("url", "");
        this.username = props.getString("username", "");
        this.password = props.getString("password", "");
        this.driver = props.getString("driver", "");
    }

    public Connection connect() throws SQLException, ClassNotFoundException {
        if (!driver.isEmpty()) Class.forName(driver);
        return url.isEmpty()? null : DriverManager.getConnection(url, username, password);
    }
}
