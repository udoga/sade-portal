package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.service.SessionService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class SessionController extends RestController {
    private final SessionService service;

    public SessionController(SessionService service) {
        super("/sessions");
        this.service = service;
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.POST, "", c -> send(c, service.login(getUser(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        addSeparate(HandlerType.BEFORE, "/*", c -> authenticate(c, getToken(c)));
    }

    private User getUser(Context c) {
        return c.bodyAsClass(User.class);
    }

    private String getToken(Context c) {
        return removePrefix(c.header("Authorization"), "Bearer ");
    }

    private void authenticate(Context c, String token) {
        if (!isLoginRequest(c)) service.authenticate(token);
    }

    private boolean isLoginRequest(Context c) {
        return c.path().equals(methods.get(1).path) && c.method().equals(methods.get(1).type);
    }

    private String removePrefix(String s, String prefix) {
        return (s != null && s.startsWith(prefix))? s.replaceFirst(prefix, "") : s;
    }
}
