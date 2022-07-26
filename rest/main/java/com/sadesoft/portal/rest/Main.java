package com.sadesoft.portal.rest;

import java.sql.Connection;
import java.util.List;

import com.sadesoft.portal.base.config.DatabaseConfig;
import com.sadesoft.portal.base.config.Props;
import com.sadesoft.portal.base.config.ServerConfig;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.core.model.Person;
import com.sadesoft.portal.core.model.Project;
import com.sadesoft.portal.core.model.Receipt;
import com.sadesoft.portal.core.model.User;
import com.sadesoft.portal.core.service.PersonService;
import com.sadesoft.portal.core.service.ProjectService;
import com.sadesoft.portal.core.service.ReceiptService;
import com.sadesoft.portal.core.service.UserService;
import com.sadesoft.portal.rest.controller.PersonController;
import com.sadesoft.portal.rest.controller.ProjectController;
import com.sadesoft.portal.rest.controller.ReceiptController;
import com.sadesoft.portal.rest.controller.UserController;
import com.sadesoft.portal.rest.repo.PersonRepo;
import com.sadesoft.portal.rest.repo.ProjectRepo;
import com.sadesoft.portal.rest.repo.ReceiptRepo;
import com.sadesoft.portal.rest.repo.UserRepo;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0) ? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new DatabaseConfig(props.getSection("database")).connect();
        ServerConfig serverConfig = new ServerConfig(props.getSection("server"));
        Repo<Person> personRepo = new PersonRepo(connection);
        Repo<Project> projectRepo = new ProjectRepo(connection);
        Repo<Receipt> receiptRepo = new ReceiptRepo(connection);
        Repo<User> userRepo = new UserRepo(connection);
        PersonService personService = new PersonService(personRepo);
        ProjectService projectService = new ProjectService(projectRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);
        UserService userService = new UserService(userRepo);
        PersonController personController = new PersonController("/api/people", personService);
        ProjectController projectController = new ProjectController("/api/projects", projectService);
        ReceiptController receiptController = new ReceiptController("/api/receipts", receiptService);
        UserController userController = new UserController("/api/users", userService);
        List<RestController> controllers = List.of(personController, projectController, receiptController,
                userController);
        RestServer server = new RestServer(serverConfig, controllers);
        server.run();
    }
}
