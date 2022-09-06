package com.sadesoft.portal.base.rest;

import java.util.Objects;

public class RestRequest {
    public final String url;
    public final String method;
    public final String body;

    public RestRequest(String url, String method, String body) {
        this.url = url;
        this.method = method;
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof RestRequest)) return false;
        RestRequest r = (RestRequest) o;
        return Objects.equals(url, r.url) && Objects.equals(method, r.method) && Objects.equals(body, r.body);
    }

}
