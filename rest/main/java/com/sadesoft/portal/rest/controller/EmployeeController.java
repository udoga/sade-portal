package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Employee;
import com.sadesoft.portal.core.service.EmployeeService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class EmployeeController extends RestController {
    public EmployeeController(EmployeeService service) {
        super("/employees");
        add(HandlerType.GET, "", c -> send(c, service.listByUserId(getUserId(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailed()));
        add(HandlerType.POST, "", c -> send(c, service.create(getEmployee(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getEmployee(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private String getUserId(Context c) {
        return c.queryParam("userId");
    }

    private Employee getEmployee(Context c) {
        return c.bodyAsClass(Employee.class);
    }
}
