package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.service.SessionService;
import com.sadesoft.portal.rest.javalin.RestController;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class SessionController extends RestController {
    public SessionController(SessionService service) {
        super("/sessions");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.POST, "", c -> send(c, service.login(getUser(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
    }

    private User getUser(Context c) {
        return c.bodyAsClass(User.class);
    }
}
