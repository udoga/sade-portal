package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.VacationCategory;
import com.sadesoft.portal.core.service.VacationCategoryService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class VacationCategoryController extends RestController {
    public VacationCategoryController(VacationCategoryService service) {
        super("/vacation-categories");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.POST, "", c -> send(c, service.create(getVacationCategory(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private VacationCategory getVacationCategory(Context c) {
        return c.bodyAsClass(VacationCategory.class);
    }
}
