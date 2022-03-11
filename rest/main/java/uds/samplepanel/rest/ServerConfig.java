package uds.samplepanel.rest;

import uds.samplepanel.base.Props;

public class ServerConfig {
    public final int port;
    public final String uiFolder;
    public final boolean uiExternal;

    public ServerConfig(Props props) {
        this.port = props.getInt("port");
        this.uiFolder = props.get("ui_folder");
        this.uiExternal = props.getBool("ui_external");
    }
}
