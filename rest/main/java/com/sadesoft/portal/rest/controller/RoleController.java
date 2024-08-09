package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Role;
import com.sadesoft.portal.core.service.RoleService;

import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class RoleController extends RestController {
    public RoleController(RoleService service) {
        super("/roles");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.GET, "/summarized", c -> send(c, service.listSummarized()));
        add(HandlerType.POST, "", c -> send(c, service.create(getRole(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getRole(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Role getRole(Context c) {
        return c.bodyAsClass(Role.class);
    }
}
