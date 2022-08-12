package com.sadesoft.portal.rest.javalin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sadesoft.portal.base.util.StringUtil;
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
        return StringUtil.getInteger(getQueryParameter(c, "pageNo", "1"));
    }

    protected int getPageSize(Context c) {
        return StringUtil.getInteger(getQueryParameter(c, "pageSize", "10"));
    }

    private String getQueryParameter(Context c, String key, String defaultValue) {
        String param = c.queryParam(key);
        return (param == null)? defaultValue : param;
    }
}
