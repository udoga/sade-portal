package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.core.model.Session;
import com.sadesoft.portal.core.service.SessionService;
import com.sadesoft.portal.rest.javalin.RestController;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class SessionController extends RestController {
    public SessionController(SessionService service) {
        super("/sessions");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
    }

    private Session getSession(Context c) {
        return c.bodyAsClass(Session.class);
    }
}
