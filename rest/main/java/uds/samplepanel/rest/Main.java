package uds.samplepanel.rest;

import uds.samplepanel.base.*;
import uds.samplepanel.core.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        DatabaseConnector connector = new DatabaseConnector(props.getSection("database"));
        ServerConfig serverConfig = new ServerConfig(props.getSection("server"));

        Repo<User> userRepo = new UserRepo(connector.connect());
        Repo<Item> itemRepo = new ListRepo<>("Item");

        UserService userService = new UserService(userRepo);
        ItemService itemService = new ItemService(itemRepo);
        MainService mainService = new MainService(userService, itemService);

        RestServer server = new RestServer(serverConfig, mainService);
        server.run();
    }
}
