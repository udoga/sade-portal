package com.sadesoft.portal.rest;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sadesoft.portal.base.error.*;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import com.sadesoft.portal.base.config.ServerConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestServer {
    private final ServerConfig config;
    private final List<RestController> controllers;
    private final Javalin server;
    private final Logger logger;

    public RestServer(ServerConfig config, List<RestController> controllers) {
        this.config = config;
        this.controllers = controllers;
        this.server = Javalin.create();
        this.logger = LoggerFactory.getLogger(RestServer.class);
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
        server.exception(RecordNotFoundError.class, (e, c) -> handle(c, e, 404));
        server.exception(InvalidFormatException.class, (e, c) -> handle(c, convert(e), 400));
        server.exception(ClientError.class, (e, c) -> handle(c, e, 400));
        server.exception(Exception.class, (e, c) -> handle(c, e, 500));
    }

    private void handle(Context c, Exception e, int status) {
        if (status >= 500) e.printStackTrace();
        else logger.info(e.getClass().getSimpleName() + ": " + e.getMessage());
        c.status(status);
        c.json(Map.of("status", status, "type", e.getClass().getSimpleName(), "message", e.getMessage()));
    }

    private Exception convert(InvalidFormatException e) {
        if (e.getTargetType().getSimpleName().equals("LocalDate"))
            return new InvalidDateError(e.getValue().toString());
        if (e.getTargetType().isEnum())
            return new InvalidEnumError(e.getTargetType().getSimpleName(), e.getValue().toString());
        if (e.getTargetType().getSimpleName().equals("Boolean"))
            return new InvalidBoolError(e.getValue().toString());
        if (Number.class.isAssignableFrom(e.getTargetType()))
            return new InvalidNumberError(e.getValue().toString());
        return e;
    }
}
