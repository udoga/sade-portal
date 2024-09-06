package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.query.AssignmentQuery;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.service.AssignmentService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class AssignmentController extends RestController {
    public AssignmentController(AssignmentService service) {
        super("/assignments");
        add(HandlerType.GET, "", c -> send(c, service.listBy(getQuery(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailedBy(getQuery(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getAssignment(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getAssignment(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Assignment getAssignment(Context c) {
        return c.bodyAsClass(Assignment.class);
    }

    private AssignmentQuery getQuery(Context c) {
        return new AssignmentQuery()
                .withUserId(c.queryParam("userId"))
                .withRoleId(c.queryParam("roleId"));
    }
}
