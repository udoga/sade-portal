package uds.samplepanel.rest;

import io.javalin.Javalin;
import io.javalin.http.Context;
import uds.samplepanel.core.User;
import uds.samplepanel.core.UserService;

public class RestServer {
    private final int port;
    private final UserService userService;
    private final Javalin server;

    public RestServer(int port, UserService userService) {
        this.port = port;
        this.userService = userService;
        this.server = Javalin.create();
        configure();
    }

    public void run() {
        server.start(port);
    }

    private void configure() {
        server.get("/users", (c) -> c.json(userService.list()));
        server.post("/users", (c) -> c.json(userService.create(getUser(c))));
        server.get("/users/{id}", (c) -> c.json(userService.read(getId(c))));
        server.put("/users/{id}", (c) -> c.json(userService.update(getId(c), getUser(c))));
        server.delete("/users/{id}", (c) -> c.json(userService.delete(getId(c))));
    }

    private int getId(Context c) {
        return Integer.parseInt(c.pathParam("id"));
    }

    private User getUser(Context c) {
        return c.bodyAsClass(User.class);
    }
}
