package uds.samplepanel.rest;

import io.javalin.Javalin;
import io.javalin.core.JavalinConfig;
import io.javalin.http.Context;
import java.util.Map;
import uds.samplepanel.base.RecordNotFound;
import uds.samplepanel.core.User;
import uds.samplepanel.core.UserService;

public class RestServer {
    private final int port;
    private final UserService userService;
    private final Javalin server;

    public RestServer(int port, UserService userService) {
        this.port = port;
        this.userService = userService;
        this.server = Javalin.create(JavalinConfig::enableCorsForAllOrigins);
        configureRequests();
        configureErrors();
    }

    public void run() {
        server.start(port);
    }

    private void configureRequests() {
        server.get("/users", (c) -> send(c, userService.list()));
        server.post("/users", (c) -> send(c, userService.create(getUser(c))));
        server.get("/users/{id}", (c) -> send(c, userService.read(getId(c))));
        server.put("/users/{id}", (c) -> send(c, userService.update(getId(c), getUser(c))));
        server.delete("/users/{id}", (c) -> send(c, userService.delete(getId(c))));
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
