package uds.samplepanel.rest;

import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.http.Context;
import io.javalin.http.staticfiles.Location;
import io.javalin.plugin.json.JavalinJackson;
import uds.samplepanel.base.RecordNotFound;
import uds.samplepanel.core.User;
import uds.samplepanel.core.UserService;

public class RestServer {
    private final ServerConfig config;
    private final UserService userService;
    private final Javalin server;

    public RestServer(ServerConfig config, UserService userService) {
        this.config = config;
        this.userService = userService;
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
        server.get("/api/users", (c) -> send(c, userService.list()));
        server.post("/api/users", (c) -> send(c, userService.create(getUser(c))));
        server.get("/api/users/{id}", (c) -> send(c, userService.read(getId(c))));
        server.put("/api/users/{id}", (c) -> send(c, userService.update(getId(c), getUser(c))));
        server.delete("/api/users/{id}", (c) -> send(c, userService.delete(getId(c))));
    }

    private int getId(Context c) {
        return Integer.parseInt(c.pathParam("id"));
    }

    private User getUser(Context c) {
        return c.bodyAsClass(User.class);
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
