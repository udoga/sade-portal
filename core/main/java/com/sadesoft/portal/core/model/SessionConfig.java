package com.sadesoft.portal.core.model;

import com.sadesoft.portal.base.model.Props;

public class SessionConfig {
    public int sessionTimeout;
    public boolean authenticationEnabled;
    public String salt;

    public SessionConfig(int sessionTimeout, boolean authenticationEnabled, String salt) {
        this.sessionTimeout = sessionTimeout;
        this.authenticationEnabled = authenticationEnabled;
        this.salt = salt;
    }

    public SessionConfig(Props props) {
        this.sessionTimeout = props.getInt("session_timeout", 0);
        this.authenticationEnabled = props.getBool("authentication_enabled", false);
        this.salt = props.getString("salt", "");
    }
}
