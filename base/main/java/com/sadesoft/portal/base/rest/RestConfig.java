package com.sadesoft.portal.base.rest;

import com.sadesoft.portal.base.model.Props;

public class RestConfig {
    public String path;
    public int port;
    public String uiFolder;
    public boolean uiExternal;

    public RestConfig(Props props) {
        this.path = props.getString("path", "");
        this.port = props.getInt("port", 7000);
        this.uiFolder = props.getString("ui_folder", "");
        this.uiExternal = props.getBool("ui_external", false);
    }
}
