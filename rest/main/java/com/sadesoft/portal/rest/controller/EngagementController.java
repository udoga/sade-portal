package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.filter.EngagementFilter;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.service.EngagementService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class EngagementController extends RestController {
    public EngagementController(EngagementService service) {
        super("/engagements");
        add(HandlerType.GET, "", c ->  send(c, service.listBy(getEngagementFilter(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailedBy(getEngagementFilter(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getEngagement(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getEngagement(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Engagement getEngagement(Context c) {
        return c.bodyAsClass(Engagement.class);
    }

    private EngagementFilter getEngagementFilter(Context c) {
        return new EngagementFilter()
                .withProjectId(c.queryParam("projectId"))
                .withEmployeeId(c.queryParam("employeeId"));
    }
}