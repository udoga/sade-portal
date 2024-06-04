package com.sadesoft.portal.rest;

import java.sql.Connection;
import com.sadesoft.portal.base.repo.JdbcConfig;
import com.sadesoft.portal.base.model.Props;
import com.sadesoft.portal.base.rest.RestConfig;
import com.sadesoft.portal.core.model.SessionConfig;
import com.sadesoft.portal.base.util.PathUtil;
import com.sadesoft.portal.base.rest.RestServer;
import com.sadesoft.portal.core.repo.*;
import com.sadesoft.portal.core.service.*;
import com.sadesoft.portal.repo.local.*;
import com.sadesoft.portal.rest.controller.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0) ? args[0] : PathUtil.findResourcePath("default.properties"));
        Connection connection = new JdbcConfig(props.getSection("jdbc")).connect();
        RestConfig restConfig = new RestConfig(props.getSection("rest"));
        SessionConfig sessionConfig = new SessionConfig(props.getSection("session"));

        EmployeeRepo employeeRepo = new LocalEmployeeRepo(connection);
        ExpenseRepo expenseRepo = new LocalExpenseRepo(connection);
        LeaveRepo leaveRepo = new LocalLeaveRepo(connection);
        ReceiptRepo receiptRepo = new LocalReceiptRepo(connection);
        UserRepo userRepo = new LocalUserRepo(connection);
        SessionRepo sessionRepo = new LocalSessionRepo(connection);
        PermissionRepo permissionRepo = new LocalPermissionRepo(connection);

        EmployeeService employeeService = new EmployeeService(employeeRepo);
        ExpenseService expenseService = new ExpenseService(expenseRepo);
        LeaveService leaveService = new LeaveService(leaveRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);
        UserService userService = new UserService(userRepo);
        SessionService sessionService = new SessionService(sessionConfig, sessionRepo, userRepo);
        PermissionService permissionService = new PermissionService(permissionRepo);

        RestServer server = new RestServer(restConfig);
        server.enrol(new EmployeeController(employeeService));
        server.enrol(new ReceiptController(receiptService));
        server.enrol(new ExpenseController(expenseService));
        server.enrol(new LeaveController(leaveService));
        server.enrol(new UserController(userService));
        server.enrol(new SessionController(sessionService));
        server.enrol(new PermissionController(permissionService));
        server.run();
    }
}
