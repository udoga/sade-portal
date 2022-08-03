package com.sadesoft.portal.rest;

import java.sql.Connection;
import java.util.List;

import com.sadesoft.portal.base.config.DatabaseConfig;
import com.sadesoft.portal.base.config.Props;
import com.sadesoft.portal.base.config.ServerConfig;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.service.*;
import com.sadesoft.portal.rest.controller.*;
import com.sadesoft.portal.rest.repo.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0) ? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new DatabaseConfig(props.getSection("database")).connect();
        ServerConfig serverConfig = new ServerConfig(props.getSection("server"));
        Repo<Expense> expenseRepo = new ExpenseRepo(connection);
        Repo<Person> personRepo = new PersonRepo(connection);
        Repo<Project> projectRepo = new ProjectRepo(connection);
        Repo<Receipt> receiptRepo = new ReceiptRepo(connection);
        Repo<User> userRepo = new UserRepo(connection);
        ExpenseService expenseService = new ExpenseService(expenseRepo);
        PersonService personService = new PersonService(personRepo);
        ProjectService projectService = new ProjectService(projectRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);
        UserService userService = new UserService(userRepo);
        ExpenseController expenseController = new ExpenseController(expenseService);
        PersonController personController = new PersonController(personService);
        ProjectController projectController = new ProjectController(projectService);
        ReceiptController receiptController = new ReceiptController(receiptService);
        UserController userController = new UserController(userService);
        List<RestController> controllers = List.of(expenseController, personController, projectController,
                receiptController, userController);
        RestServer server = new RestServer(serverConfig, controllers);
        server.run();
    }
}
