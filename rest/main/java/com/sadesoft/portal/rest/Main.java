package com.sadesoft.portal.rest;

import java.sql.Connection;
import java.util.List;
import com.sadesoft.portal.base.config.DatabaseConfig;
import com.sadesoft.portal.base.config.Props;
import com.sadesoft.portal.base.config.ServerConfig;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.core.model.Item;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.service.ItemService;
import com.sadesoft.portal.core.service.ProjectService;
import com.sadesoft.portal.core.service.UserService;
import com.sadesoft.portal.rest.controller.ItemController;
import com.sadesoft.portal.rest.controller.ProjectController;
import com.sadesoft.portal.rest.controller.UserController;
import com.sadesoft.portal.rest.repo.ItemRepo;
import com.sadesoft.portal.rest.repo.ProjectRepo;
import com.sadesoft.portal.rest.repo.UserRepo;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0)? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new DatabaseConfig(props.getSection("database")).connect();
        ServerConfig serverConfig = new ServerConfig(props.getSection("server"));
        Repo<Item> itemRepo = new ItemRepo(connection);
        Repo<Project> projectRepo = new ProjectRepo(connection);
        Repo<User> userRepo = new UserRepo(connection);
        ItemService itemService = new ItemService(itemRepo);
        ProjectService projectService = new ProjectService(projectRepo);
        UserService userService = new UserService(userRepo);
        ItemController itemController = new ItemController("/api/items", itemService);
        ProjectController projectController = new ProjectController("/api/projects", projectService);
        UserController userController = new UserController("/api/users", userService);
        RestServer server = new RestServer(serverConfig, List.of(itemController, projectController, userController));
        server.run();
    }
}
