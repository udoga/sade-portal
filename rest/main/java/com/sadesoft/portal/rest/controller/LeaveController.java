package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.ApprovalStatus;
import com.sadesoft.portal.core.model.Leave;
import com.sadesoft.portal.core.service.LeaveService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class LeaveController extends RestController {
    public LeaveController(LeaveService service) {
        super("/leaves");
        add(HandlerType.GET, "", c ->  send(c, service.paginateByStatus(getPageNo(c), getPageSize(c), getStatus(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getLeave(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getLeave(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Leave getLeave(Context c) {
        return c.bodyAsClass(Leave.class);
    }

    private ApprovalStatus getStatus(Context c) {
        String queryParameter = getQueryParameter(c, "status", null);
        return (queryParameter == null)? null : ApprovalStatus.valueOf(queryParameter);
    }
}
