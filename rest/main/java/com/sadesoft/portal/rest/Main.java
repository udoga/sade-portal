package com.sadesoft.portal.rest;

import java.sql.Connection;

import com.sadesoft.portal.base.config.*;
import com.sadesoft.portal.base.repo.Repo;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.core.model.*;
import com.sadesoft.portal.core.service.*;
import com.sadesoft.portal.repo.kolay.KolayConfig;
import com.sadesoft.portal.repo.kolay.KolayPersonRepo;
import com.sadesoft.portal.repo.local.*;
import com.sadesoft.portal.rest.controller.*;
import com.sadesoft.portal.rest.javalin.RestServer;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0) ? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new DatabaseConfig(props.getSection("database")).connect();
        KolayConfig kolayConfig = new KolayConfig(props.getSection("kolay"));
        ServerConfig serverConfig = new ServerConfig(props.getSection("server"));
        Repo<Expense> expenseRepo = new LocalExpenseRepo(connection);
        Repo<Leave> leaveRepo = new LocalLeaveRepo(connection);
        Repo<Person> personRepo = kolayConfig.enabled? new KolayPersonRepo(kolayConfig) : new LocalPersonRepo(connection);
        Repo<Receipt> receiptRepo = new LocalReceiptRepo(connection);
        Repo<User> userRepo = new LocalUserRepo(connection);

        ExpenseService expenseService = new ExpenseService(expenseRepo);
        LeaveService leaveService = new LeaveService(leaveRepo);
        PersonService personService = new PersonService(personRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);
        UserService userService = new UserService(userRepo);

        RestServer server = new RestServer(serverConfig);
        server.enrol(new PersonController(personService));
        server.enrol(new ReceiptController(receiptService));
        server.enrol(new ExpenseController(expenseService));
        server.enrol(new LeaveController(leaveService));
        server.enrol(new UserController(userService));

        server.run();
    }
}
