package com.sadesoft.portal.rest;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sadesoft.portal.base.error.MissingFieldError;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import com.sadesoft.portal.base.error.RecordNotFound;
import com.sadesoft.portal.base.config.ServerConfig;

public class RestServer {
    private final ServerConfig config;
    private final List<RestController> controllers;
    private final Javalin server;

    public RestServer(ServerConfig config, List<RestController> controllers) {
        this.config = config;
        this.controllers = controllers;
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
        for (RestController controller: controllers)
            for (RestMethod method: controller.methods)
                server.addHandler(method.type, controller.path + method.path, method.handler);
    }

    private void configureErrors() {
        server.exception(MissingFieldError.class, (e, c) -> handle(c, e, 400));
        server.exception(RecordNotFound.class, (e, c) -> handle(c, e, 404));
        server.exception(Exception.class, (e, c) -> handle(c, e, 500));
    }

    private void handle(Context c, Exception e, int status) {
        if (status >= 500) e.printStackTrace();
        c.status(status);
        c.json(Map.of("status", status, "type", e.getClass().getSimpleName(), "message", e.getMessage()));
    }
}
