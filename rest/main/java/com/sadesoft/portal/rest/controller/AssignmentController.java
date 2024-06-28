package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Assignment;
import com.sadesoft.portal.core.service.AssignmentService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class AssignmentController extends RestController {
    public AssignmentController(AssignmentService service) {
        super("/assignments");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.GET, "/with-role", c -> send(c, service.listWithRole()));
        add(HandlerType.POST, "", c -> send(c, service.create(getAssignment(c))));
        add(HandlerType.DELETE, "", c -> send(c, service.deleteByValues(getQueriedAssignment(c))));
    }

    private Assignment getAssignment(Context c) {
        return c.bodyAsClass(Assignment.class);
    }

    private Assignment getQueriedAssignment(Context c) {
        return new Assignment(null, c.queryParam("userId"), c.queryParam("roleId"));
    }
}
