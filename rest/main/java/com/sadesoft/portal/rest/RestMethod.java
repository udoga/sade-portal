package com.sadesoft.portal.rest;

import io.javalin.http.Handler;
import io.javalin.http.HandlerType;

public class RestMethod {
    public HandlerType type;
    public String path;
    public Handler handler;

    public RestMethod(HandlerType type, String path, Handler handler) {
        this.type = type;
        this.path = path;
        this.handler = handler;
    }
}
