package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Vacation;
import com.sadesoft.portal.core.service.VacationService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class VacationController extends RestController {
    public VacationController(VacationService service) {
        super("/vacations");
        add(HandlerType.GET, "", c ->  send(c, service.listByStatus(getStatus(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailed()));
        add(HandlerType.POST, "", c -> send(c, service.create(getVacation(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.readDetailed(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getVacation(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Vacation getVacation(Context c) {
        return c.bodyAsClass(Vacation.class);
    }

    private String getStatus(Context c) {
        return getQueryParameter(c, "status", null);
    }
}
