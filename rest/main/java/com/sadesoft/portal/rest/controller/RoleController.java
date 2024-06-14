package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.service.RoleService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class RoleController extends RestController {
    public RoleController(RoleService service) {
        super("/roles");
        add(HandlerType.GET, "/", c -> send(c, service.listByUserId(getUserId(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
    }

    private String getUserId(Context c) {
        return getQueryParameter(c, "userId", null);
    }
}
