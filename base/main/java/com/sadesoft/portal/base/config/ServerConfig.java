package com.sadesoft.portal.base.config;

public class ServerConfig {
    public final String path;
    public final int port;
    public final String uiFolder;
    public final boolean uiExternal;

    public ServerConfig(Props props) {
        this.path = props.getString("path");
        this.port = props.getInt("port");
        this.uiFolder = props.getString("ui_folder");
        this.uiExternal = props.getBool("ui_external");
    }
}
