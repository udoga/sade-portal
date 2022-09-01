package com.sadesoft.portal.repo.kolay;

import com.sadesoft.portal.base.config.Props;

public class KolayConfig {
    public boolean enabled;
    public String url;
    public String token;

    public KolayConfig(Props props) {
        this.enabled = props.getBool("enabled", false);
        this.url = props.getString("url", "");
        this.token = props.getString("token", "");
    }
}
