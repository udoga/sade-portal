package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Position;
import com.sadesoft.portal.core.service.PositionService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class PositionController extends RestController {
    public PositionController(PositionService service) {
        super("/positions");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.POST, "", c -> send(c, service.create(getPosition(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getPosition(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Position getPosition(Context c) {
        return c.bodyAsClass(Position.class);
    }
}
