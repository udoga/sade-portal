package com.sadesoft.portal.base.auth;

import com.sadesoft.portal.base.model.Props;

public class AuthConfig {
    public boolean authenticationEnabled;
    public boolean authorizationEnabled;
    public int sessionTimeout;
    public String salt;

    public AuthConfig(boolean authenticationEnabled, boolean authorizationEnabled, int sessionTimeout, String salt) {
        this.authenticationEnabled = authenticationEnabled;
        this.authorizationEnabled = authorizationEnabled;
        this.sessionTimeout = sessionTimeout;
        this.salt = salt;
    }

    public AuthConfig(Props props) {
        this.authenticationEnabled = props.getBool("authentication_enabled", false);
        this.authorizationEnabled = props.getBool("authorization_enabled", false);
        this.sessionTimeout = props.getInt("session_timeout", 0);
        this.salt = props.getString("salt", "");
    }
}
