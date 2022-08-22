package com.sadesoft.portal.rest;

import java.sql.Connection;
import java.util.List;

import com.sadesoft.portal.base.config.*;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.service.*;
import com.sadesoft.portal.rest.controller.*;
import com.sadesoft.portal.rest.javalin.RestServer;
import com.sadesoft.portal.rest.kolay.*;
import com.sadesoft.portal.rest.repo.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0) ? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new DatabaseConfig(props.getSection("database")).connect();
        KolayConfig kolayConfig = new KolayConfig(props.getSection("kolay"));
        ServerConfig serverConfig = new ServerConfig(props.getSection("server"));
        Repo<Expense> expenseRepo = new ExpenseRepo(connection);
        Repo<Leave> leaveRepo = new LeaveRepo(connection);
        Repo<Person> personRepo = kolayConfig.enabled? new PersonKolayRepo(kolayConfig) : new PersonRepo(connection);
        Repo<Receipt> receiptRepo = new ReceiptRepo(connection);
        Repo<User> userRepo = new UserRepo(connection);
        ExpenseService expenseService = new ExpenseService(expenseRepo);
        LeaveService LeaveService = new LeaveService(leaveRepo);
        PersonService personService = new PersonService(personRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);
        UserService userService = new UserService(userRepo);
        PersonController personController = new PersonController(personService);
        ReceiptController receiptController = new ReceiptController(receiptService);
        ExpenseController expenseController = new ExpenseController(expenseService);
        LeaveController leaveController = new LeaveController(LeaveService);
        UserController userController = new UserController(userService);
        RestServer server = new RestServer(serverConfig, List.of(expenseController, leaveController, personController,
                receiptController, userController));
        server.run();
    }
}
