package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.query.WorklogQuery;
import com.sadesoft.portal.core.model.Worklog;
import com.sadesoft.portal.core.order.WorklogOrder;
import com.sadesoft.portal.core.service.WorklogService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

import java.time.LocalDate;

public class WorklogController extends RestController {
    public WorklogController(WorklogService service) {
        super("/worklogs");
        add(HandlerType.GET, "", c -> send(c, service.listBy(getCallerId(c), getQuery(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailedBy(getCallerId(c), getQuery(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getCallerId(c), getWorklog(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getCallerId(c), getId(c))));
        add(HandlerType.GET, "/detailed/{id}", c -> send(c, service.readDetailed(getCallerId(c), getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getCallerId(c), getId(c), getWorklog(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getCallerId(c), getId(c))));
    }

    private Worklog getWorklog(Context c) {
        return c.bodyAsClass(Worklog.class);
    }

    private WorklogQuery getQuery(Context c) {
        return new WorklogQuery()
                .withOffset(c.queryParamAsClass("offset", Integer.class).getOrDefault(0))
                .withProjectId(c.queryParam("projectId"))
                .withEmployeeId(c.queryParam("employeeId"))
                .withMinDate(c.queryParamAsClass("minDate", LocalDate.class).getOrDefault(null))
                .withMaxDate(c.queryParamAsClass("maxDate", LocalDate.class).getOrDefault(null))
                .withOrder(getEnum(WorklogOrder.class, getQueryParameter(c, "order", "id")));
    }
}
