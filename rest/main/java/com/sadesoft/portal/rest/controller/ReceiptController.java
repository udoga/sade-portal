package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.service.ReceiptService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class ReceiptController extends RestController {
    public ReceiptController(ReceiptService service) {
        super("/receipts");
        add(HandlerType.GET, "", c -> send(c, service.paginate(getPageNo(c), getPageSize(c))));
        add(HandlerType.POST, "", c -> send(c, service.create(getReceipt(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getReceipt(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Receipt getReceipt(Context c) {
        return c.bodyAsClass(Receipt.class);
    }
}
