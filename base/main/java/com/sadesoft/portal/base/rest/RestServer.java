package com.sadesoft.portal.base.rest;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.sadesoft.portal.base.error.*;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.json.JavalinJackson;
import io.javalin.plugin.bundled.CorsPluginConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RestServer {
    private final RestConfig config;
    private final Javalin server;
    private final Logger logger;

    public RestServer(RestConfig config) {
        this.config = config;
        this.server = Javalin.create();
        this.logger = LoggerFactory.getLogger(RestServer.class);
        configure();
    }

    public void run() {
        server.start(config.port);
    }

    public void enrol(RestController controller) {
        for (RestMethod method: controller.methods) enrol(method);
    }

    private void enrol(RestMethod method) {
        method.path = config.path + method.path;
        server.addHttpHandler(method.type, method.path, method.handler);
    }

    private void configure() {
        configureServer();
        configureMapper();
        configureErrors();
    }

    private void configureServer() {
        JavalinConfig c = server.unsafeConfig();
        c.bundledPlugins.enableCors(cors -> cors.addRule(CorsPluginConfig.CorsRule::anyHost));
        c.validation.register(LocalDate.class, v -> LocalDate.parse(v, DateTimeFormatter.ISO_LOCAL_DATE));
        if (!config.uiFolder.isEmpty())
            c.staticFiles.add(config.uiFolder, config.uiExternal? Location.EXTERNAL : Location.CLASSPATH);
    }

    private void configureMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        server.unsafeConfig().jsonMapper(new JavalinJackson(mapper, false));
    }

    private void configureErrors() {
        server.exception(AuthorizationError.class, (e, c) -> handle(c, e, 401));
        server.exception(RecordNotFoundError.class, (e, c) -> handle(c, e, 404));
        server.exception(InvalidFormatException.class, (e, c) -> handle(c, convertFormatException(e), 400));
        server.exception(ClientError.class, (e, c) -> handle(c, e, 400));
        server.exception(Exception.class, (e, c) -> handle(c, e));
    }

    private void handle(Context c, Exception e) {
        if (e.getCause() instanceof SQLException) handleSqlException(c, (SQLException) e.getCause());
        else handle(c, e, 500);
    }

    private void handleSqlException(Context c, SQLException e) {
        if (e.getSQLState().equals("23505")) throw new ClientError("Duplicate value for a unique field.");
        else if (e.getSQLState().equals("23503")) throw new ClientError("This record is used in another table.");
        else handle(c, e, 500);
    }

    private void handle(Context c, Exception e, int status) {
        if (status >= 500) e.printStackTrace();
        else logger.info("{}: {}", e.getClass().getSimpleName(), e.getMessage());
        c.status(status);
        c.json(Map.of("error", new RestError(status, e.getClass().getSimpleName(), e.getMessage())));
    }

    private Exception convertFormatException(InvalidFormatException e) {
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
