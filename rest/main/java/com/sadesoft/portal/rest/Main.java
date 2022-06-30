package com.sadesoft.portal.rest;

import java.sql.Connection;

import com.sadesoft.portal.base.*;
import com.sadesoft.portal.core.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new DatabaseConnector(props.getSection("database")).connect();
        ServerConfig serverConfig = new ServerConfig(props.getSection("server"));
        Repo<User> userRepo = new UserRepo(connection);
        Repo<Item> itemRepo = new ItemRepo(connection);
        UserService userService = new UserService(userRepo);
        ItemService itemService = new ItemService(itemRepo);
        MainService mainService = new MainService(userService, itemService);
        RestServer server = new RestServer(serverConfig, mainService);
        server.run();
    }
}
