package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.service.UserService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class UserController extends RestController {
    public UserController(UserService service) {
        super("/users");
        add(HandlerType.GET, "", c -> send(c, service.list(getCallerId(c))));
        add(HandlerType.GET, "/options", c -> send(c, service.listOptions(getCallerId(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getCallerId(c), getUser(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getCallerId(c), getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getCallerId(c), getId(c), getUser(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getCallerId(c), getId(c))));
    }

    private User getUser(Context c) {
        return c.bodyAsClass(User.class);
    }
}
