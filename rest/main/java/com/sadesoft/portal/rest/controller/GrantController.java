package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.filter.GrantFilter;
import com.sadesoft.portal.core.model.Grant;
import com.sadesoft.portal.core.service.GrantService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class GrantController extends RestController {
    public GrantController(GrantService service) {
        super("/grants");
        add(HandlerType.GET, "", c -> send(c, service.listBy(getFilter(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailedBy(getFilter(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getGrant(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Grant getGrant(Context c) {
        return c.bodyAsClass(Grant.class);
    }

    private GrantFilter getFilter(Context c) {
        return new GrantFilter()
                .withOffset(c.queryParamAsClass("offset", Integer.class).getOrDefault(0))
                .withRoleId(c.queryParam("roleId"))
                .withPermissionId(c.queryParam("permissionId"));
    }
}
