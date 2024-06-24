package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.WorklogType;
import com.sadesoft.portal.core.service.WorklogTypeService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class WorklogTypeController extends RestController {
    public WorklogTypeController(WorklogTypeService service) {
        super("/worklog-types");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.POST, "", c -> send(c, service.create(getWorklogType(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getWorklogType(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private WorklogType getWorklogType(Context c) {
        return c.bodyAsClass(WorklogType.class);
    }
}
