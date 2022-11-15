package com.sadesoft.portal.rest;

import java.sql.Connection;

import com.sadesoft.portal.base.config.*;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.core.repo.*;
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

        ExpenseRepo expenseRepo = new LocalExpenseRepo(connection);
        LeaveRepo leaveRepo = new LocalLeaveRepo(connection);
        PersonRepo personRepo = kolayConfig.enabled? new KolayPersonRepo(kolayConfig) : new LocalPersonRepo(connection);
        ReceiptRepo receiptRepo = new LocalReceiptRepo(connection);
        UserRepo userRepo = new LocalUserRepo(connection);
        SessionRepo sessionRepo = new LocalSessionRepo(connection);
        PermissionRepo permissionRepo = new LocalPermissionRepo(connection);

        ExpenseService expenseService = new ExpenseService(expenseRepo);
        LeaveService leaveService = new LeaveService(leaveRepo);
        PersonService personService = new PersonService(personRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);
        UserService userService = new UserService(userRepo);
        SessionService sessionService = new SessionService(sessionRepo, userRepo);
        PermissionService permissionService = new PermissionService(permissionRepo);

        RestServer server = new RestServer(serverConfig);
        server.enrol(new PersonController(personService));
        server.enrol(new ReceiptController(receiptService));
        server.enrol(new ExpenseController(expenseService));
        server.enrol(new LeaveController(leaveService));
        server.enrol(new UserController(userService));
        server.enrol(new SessionController(sessionService));
        server.enrol(new PermissionController(permissionService));

        server.run();
    }
}
