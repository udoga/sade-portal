package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.core.model.Item;
import com.sadesoft.portal.core.service.ItemService;
import com.sadesoft.portal.rest.RestController;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class ItemController extends RestController {
    public ItemController(String path, ItemService service) {
        super(path);
        add(HandlerType.GET, "", c -> send(c, service.list()));
        add(HandlerType.POST, "", c -> send(c, service.create(getItem(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getItem(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Item getItem(Context c) {
        return c.bodyAsClass(Item.class);
    }
}
