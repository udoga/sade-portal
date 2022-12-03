package com.sadesoft.portal.rest.javalin;

import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sadesoft.portal.base.error.*;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.json.JavalinJackson;
import com.sadesoft.portal.base.config.ServerConfig;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RestServer {
    private final ServerConfig config;
    private final Javalin server;
    private final Logger logger;

    public RestServer(ServerConfig config) {
        this.config = config;
        this.server = Javalin.create();
        this.logger = LoggerFactory.getLogger(RestServer.class);
        configure();
    }

    public void enrol(RestController controller) {
        for (RestMethod method: controller.methods) enrol(method);
    }

    public void run() {
        server.start(config.port);
    }

    private void enrol(RestMethod method) {
        method.path = config.path + method.path;
        server.addHandler(method.type, method.path, method.handler);
    }

    private void configure() {
        configureServer();
        configureMapper();
        configureErrors();
    }

    private void configureServer() {
        server.cfg.plugins.enableCors(corsContainer -> corsContainer.add(CorsPluginConfig::anyHost));
        if (!config.uiFolder.isEmpty())
            server.cfg.staticFiles.add(config.uiFolder, config.uiExternal? Location.EXTERNAL : Location.CLASSPATH);
    }

    private void configureMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        server.cfg.jsonMapper(new JavalinJackson(mapper));
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
        c.json(Map.of("error", new RestError(status, e.getClass().getSimpleName(), e.getMessage())));
    }

    private Exception convert(InvalidFormatException e) {
        Class<?> type = e.getTargetType();
        String typeName = type.getSimpleName();
        String value = e.getValue().toString();
        if (Number.class.isAssignableFrom(type)) return new InvalidNumberError(value);
        if (type.isEnum()) return new InvalidEnumError(typeName, value);
        if (typeName.equals("LocalDate")) return new InvalidDateError(value);
        if (typeName.equals("Boolean")) return new InvalidBoolError(value);
        return e;
    }
}
