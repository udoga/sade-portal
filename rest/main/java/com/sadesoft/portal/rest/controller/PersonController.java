package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Person;
import com.sadesoft.portal.core.service.PersonService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class PersonController extends RestController {
    public PersonController(PersonService service) {
        super("/people");
        add(HandlerType.GET, "", c -> send(c, service.listPage(getPageNo(c), getPageSize(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getPerson(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getPerson(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Person getPerson(Context c) {
        return c.bodyAsClass(Person.class);
    }
}
