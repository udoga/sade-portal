package uds.samplepanel.rest;

import uds.samplepanel.base.ListRepo;
import uds.samplepanel.base.Props;
import uds.samplepanel.base.PathUtil;
import uds.samplepanel.base.Repo;
import uds.samplepanel.core.User;
import uds.samplepanel.core.UserService;

public class Main {
    public static void main(String[] args) {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        Repo<User> userRepo = new ListRepo<>("User");
        UserService userService = new UserService(userRepo);
        ServerConfig config = new ServerConfig(props.getSection("server"));
        RestServer server = new RestServer(config, userService);
        server.run();
    }
}
