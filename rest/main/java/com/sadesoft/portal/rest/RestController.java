package com.sadesoft.portal.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HandlerType;

public class RestController {
    protected String path;
    protected List<RestMethod> methods;

    protected RestController(String path) {
        this.path = path;
        this.methods = new ArrayList<>();
    }

    protected void add(HandlerType type, String path, Handler handler) {
        this.methods.add(new RestMethod(type, path, handler));
    }

    protected int getId(Context c) {
        return Integer.parseInt(c.pathParam("id"));
    }

    protected void send(Context c, Object data) {
        c.json(Map.of("data", data));
    }

    protected int getPageNo(Context c) {
        String param = c.queryParam("pageNo");
        return (param == null)? 1 : Integer.parseInt(param);
    }

    protected int getPageSize(Context c) {
        String param = c.queryParam("pageSize");
        return (param == null)? 10 : Integer.parseInt(param);
    }
}
