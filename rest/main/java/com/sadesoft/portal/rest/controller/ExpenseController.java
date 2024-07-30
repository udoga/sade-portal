package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Expense;
import com.sadesoft.portal.core.service.ExpenseService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class ExpenseController extends RestController {
    public ExpenseController(ExpenseService service) {
        super("/expenses");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailed()));
        add(HandlerType.POST, "", c -> send(c, service.create(getExpense(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.GET, "/detailed/{id}", c -> send(c, service.readDetailed(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getExpense(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Expense getExpense(Context c) {
        return c.bodyAsClass(Expense.class);
    }
}
