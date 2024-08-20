package com.sadesoft.portal.base.rest;

import com.sadesoft.portal.base.error.InvalidEnumError;
import io.javalin.http.Context;
import io.javalin.http.Handler;
import io.javalin.http.HandlerType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RestController {
    protected String path;
    protected List<RestMethod> methods;

    protected RestController(String path) {
        this.path = path;
        this.methods = new ArrayList<>();
    }

    protected void add(HandlerType type, String path, Handler handler) {
        addSeparate(type, this.path + path, handler);
    }

    protected void addSeparate(HandlerType type, String path, Handler handler) {
        this.methods.add(new RestMethod(type, path, handler));
    }

    protected String getId(Context c) {
        return c.pathParam("id");
    }

    protected void send(Context c, Object data) {
        c.json(Map.of("data", data));
    }

    protected String getQueryParameter(Context c, String key, String defaultValue) {
        String value = c.queryParam(key);
        return (value == null)? defaultValue : value;
    }

    protected  <T extends Enum<T>> T getEnum(Class<T> enumType, String value) {
        try {
            return Enum.valueOf(enumType, value.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new InvalidEnumError(enumType.getSimpleName(), value);
        }
    }
}
