package uds.samplepanel.rest;

import uds.samplepanel.base.ListRepo;
import uds.samplepanel.base.Props;
import uds.samplepanel.base.PathUtil;
import uds.samplepanel.base.Repo;
import uds.samplepanel.core.*;

public class Main {
    public static void main(String[] args) {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        Repo<User> userRepo = new ListRepo<>("User");
        Repo<Item> itemRepo = new ListRepo<>("Item");
        UserService userService = new UserService(userRepo);
        ItemService itemService = new ItemService(itemRepo);
        MainService mainService = new MainService(userService, itemService);
        ServerConfig config = new ServerConfig(props.getSection("server"));
        RestServer server = new RestServer(config, mainService);
        server.run();
    }
}
