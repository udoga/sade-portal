package com.sadesoft.portal.base.config;

public class SessionConfig {
    public int sessionTimeout;
    public boolean authenticationEnabled;

    public SessionConfig(int sessionTimeout, boolean authenticationEnabled) {
        this.sessionTimeout = sessionTimeout;
        this.authenticationEnabled = authenticationEnabled;
    }

    public SessionConfig(Props props) {
        this.sessionTimeout = props.getInt("session_timeout", 0);
        this.authenticationEnabled = props.getBool("authentication_enabled", false);
    }
}
