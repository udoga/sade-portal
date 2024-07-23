package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.service.WorklogService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class WorklogController extends RestController {
    public WorklogController(WorklogService service) {
        super("/worklogs");
        add(HandlerType.GET, "", c -> send(c, service.listBy(getAfterId(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailedBy(getAfterId(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getWorklog(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.readDetailed(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getWorklog(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Worklog getWorklog(Context c) {
        return c.bodyAsClass(Worklog.class);
    }

    private String getAfterId(Context c) {
        return c.queryParam("afterId");
    }
}
