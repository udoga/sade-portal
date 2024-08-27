package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Engagement;
import com.sadesoft.portal.core.service.EngagementService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class EngagementController extends RestController {
    public EngagementController(EngagementService service) {
        super("/engagements");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.POST, "", c -> send(c, service.create(getEngagement(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getEngagement(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Engagement getEngagement(Context c) {
        return c.bodyAsClass(Engagement.class);
    }
}