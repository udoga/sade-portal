package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Sector;
import com.sadesoft.portal.core.service.SectorService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class SectorController extends RestController {
    public SectorController(SectorService service) {
        super("/sectors");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.GET, "/summarized", c -> send(c, service.listSummarized()));
        add(HandlerType.POST, "", c -> send(c, service.create(getSector(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Sector getSector(Context c) {
        return c.bodyAsClass(Sector.class);
    }
}
