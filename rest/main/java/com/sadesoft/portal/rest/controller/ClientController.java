package com.sadesoft.portal.rest.controller;

import com.sadesoft.portal.base.rest.RestController;
import com.sadesoft.portal.core.filter.ClientFilter;
import com.sadesoft.portal.core.model.Client;
import com.sadesoft.portal.core.service.ClientService;
import io.javalin.http.Context;
import io.javalin.http.HandlerType;

public class ClientController extends RestController {
    public ClientController(ClientService service) {
        super("/clients");
        add(HandlerType.GET, "", c -> send(c, service.listBy(getClientFilter(c))));
        add(HandlerType.GET, "/detailed", c -> send(c, service.listDetailedBy(getClientFilter(c))));
        add(HandlerType.GET, "/summarized", c -> send(c, service.listSummarized()));
        add(HandlerType.POST, "", c -> send(c, service.create(getClient(c))));
        add(HandlerType.GET, "/{id}", c -> send(c, service.read(getId(c))));
        add(HandlerType.GET, "/detailed/{id}", c -> send(c, service.readDetailed(getId(c))));
        add(HandlerType.PUT, "/{id}", c -> send(c, service.update(getId(c), getClient(c))));
        add(HandlerType.DELETE, "/{id}", c -> send(c, service.delete(getId(c))));
    }

    private Client getClient(Context c) {
        return c.bodyAsClass(Client.class);
    }

    private ClientFilter getClientFilter(Context c) {
        return new ClientFilter()
                .withOffset(c.queryParamAsClass("offset", Integer.class).getOrDefault(0));
    }
}
