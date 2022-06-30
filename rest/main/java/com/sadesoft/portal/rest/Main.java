package com.sadesoft.portal.rest;

import java.sql.Connection;
import com.sadesoft.portal.base.config.DatabaseConfig;
import com.sadesoft.portal.base.config.Props;
import com.sadesoft.portal.base.config.ServerConfig;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.core.model.Item;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.service.ItemService;
import com.sadesoft.portal.core.service.MainService;
import com.sadesoft.portal.core.service.UserService;
import com.sadesoft.portal.rest.repo.ItemRepo;
import com.sadesoft.portal.rest.repo.UserRepo;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new DatabaseConfig(props.getSection("database")).connect();
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
