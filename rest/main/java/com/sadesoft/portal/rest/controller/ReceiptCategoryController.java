package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.model.ReceiptCategory;
import com.sadesoft.portal.core.service.ReceiptCategoryService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class ReceiptCategoryController extends RestController {
    public ReceiptCategoryController(ReceiptCategoryService service) {
        super("/receipt-categories");
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.GET, "/options", c -> send(c, service.listOptions()));
        add(HandlerType.POST, "", c -> send(c, service.create(getReceiptCategory(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private ReceiptCategory getReceiptCategory(Context c) {
        return c.bodyAsClass(ReceiptCategory.class);
    }
}
