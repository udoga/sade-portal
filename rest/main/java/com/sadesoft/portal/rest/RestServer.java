package com.sadesoft.portal.rest;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import com.sadesoft.portal.base.error.RecordNotFound;
import com.sadesoft.portal.base.config.ServerConfig;
import com.sadesoft.portal.core.model.Item;
import com.sadesoft.portal.core.service.MainService;
import com.sadesoft.portal.core.model.User;

public class RestServer {
    private final ServerConfig config;
    private final MainService service;
    private final Javalin server;

    public RestServer(ServerConfig config, MainService service) {
        this.config = config;
        this.service = service;
        this.server = Javalin.create();
        configure();
    }

    public void run() {
        server.start(config.port);
    }

    private void configure() {
        configureServer();
        configureMapper();
        configureHandlers();
        configureErrors();
    }

    private void configureServer() {
        server._conf.enableCorsForAllOrigins();
        server._conf.addStaticFiles(config.uiFolder, config.uiExternal? Location.EXTERNAL : Location.CLASSPATH);
    }

    private void configureMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        server._conf.jsonMapper(new JavalinJackson(mapper));
    }

    private void configureHandlers() {
        server.get("/api/users", (c) -> send(c, service.listUsers()));
        server.post("/api/users", (c) -> send(c, service.createUser(getUser(c))));
        server.get("/api/users/{id}", (c) -> send(c, service.readUser(getId(c))));
        server.put("/api/users/{id}", (c) -> send(c, service.updateUser(getId(c), getUser(c))));
        server.delete("/api/users/{id}", (c) -> send(c, service.deleteUser(getId(c))));
        server.get("/api/items", (c) -> send(c, service.listItems()));
        server.post("/api/items", (c) -> send(c, service.createItem(getItem(c))));
        server.get("/api/items/{id}", (c) -> send(c, service.readItem(getId(c))));
        server.put("/api/items/{id}", (c) -> send(c, service.updateItem(getId(c), getItem(c))));
        server.delete("/api/items/{id}", (c) -> send(c, service.deleteItem(getId(c))));
    }

    private int getId(Context c) {
        return Integer.parseInt(c.pathParam("id"));
    }

    private User getUser(Context c) {
        return c.bodyAsClass(User.class);
    }

    private Item getItem(Context c) {
        return c.bodyAsClass(Item.class);
    }

    private void send(Context c, Object data) {
        c.json(Map.of("data", data));
    }

    private void configureErrors() {
        server.exception(RecordNotFound.class, (e, c) -> handle(c, e, 404));
        server.exception(Exception.class, (e, c) -> handle(c, e, 500));
    }

    private void handle(Context c, Exception e, int status) {
        if (status >= 500) e.printStackTrace();
        c.status(status);
        c.json(Map.of("status", status, "type", e.getClass().getSimpleName(), "message", e.getMessage()));
    }
}
