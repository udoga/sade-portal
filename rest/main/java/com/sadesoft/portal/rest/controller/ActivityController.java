package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Activity;
import com.sadesoft.portal.core.service.ActivityService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class ActivityController extends RestController {
    public ActivityController(ActivityService service) {
        super("/activities");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.GET, "/options", c -> send(c, service.listOptions()));
        add(HandlerType.POST, "", c -> send(c, service.create(getActivity(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getActivity(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Activity getActivity(Context c) {
        return c.bodyAsClass(Activity.class);
    }
}
