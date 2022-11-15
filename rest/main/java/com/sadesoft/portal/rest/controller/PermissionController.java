package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.core.service.PermissionService;
import com.sadesoft.portal.rest.javalin.RestController;
import io.javalin.http.HandlerType;

public class PermissionController extends RestController {
    public PermissionController(PermissionService service) {
        super("/permissions");
        add(HandlerType.GET, "/", c -> send(c, service.list()));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
    }
}
