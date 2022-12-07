package com.sadesoft.portal.base.config;

public class ServerConfig {
    public String path;
    public int port;
    public String uiFolder;
    public boolean uiExternal;
    public int sessionTimeout;
    public boolean authenticationEnabled;

    public ServerConfig(Props props) {
        this.path = props.getString("path", "");
        this.port = props.getInt("port", 7000);
        this.uiFolder = props.getString("ui_folder", "");
        this.uiExternal = props.getBool("ui_external", false);
        this.sessionTimeout = props.getInt("session_timeout", 0);
        this.authenticationEnabled = props.getBool("authentication_enabled", false);
    }
}
