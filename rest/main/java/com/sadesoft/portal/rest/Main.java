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
import com.sadesoft.portal.repo.opm.*;
import com.sadesoft.portal.rest.controller.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Props props = new Props((args.length > 0) ? args[0] : PathUtil.findResourcePath("default.properties"));
        RestConfig restConfig = new RestConfig(props.getSection("rest"));
        SessionConfig sessionConfig = new SessionConfig(props.getSection("session"));
        Connection local = new JdbcConfig(props.getSection(props.getString("repo.local"))).connect();
        Connection opm = new JdbcConfig(props.getSection(props.getString("repo.opm"))).connect();

        UserRepo userRepo = (opm == null)? new LocalUserRepo(local) : new OpmUserRepo(opm);
        SessionRepo sessionRepo = new LocalSessionRepo(local);
        PermissionRepo permissionRepo = (opm == null)? new LocalPermissionRepo(local) : new OpmPermissionRepo(opm);
        RoleRepo roleRepo = (opm == null)? new LocalRoleRepo(local) : new OpmRoleRepo(opm);
        AssignmentRepo assignmentRepo = (opm == null)? new LocalAssignmentRepo(local) : new OpmAssignmentRepo(opm);
        EmployeeRepo employeeRepo = (opm == null)? new LocalEmployeeRepo(local) : new OpmEmployeeRepo(opm);
        VacationRepo vacationRepo = (opm == null)? new LocalVacationRepo(local) : new OpmVacationRepo(opm);
        ExpenseRepo expenseRepo = (opm == null)? new LocalExpenseRepo(local) : new OpmExpenseRepo(opm);
        ReceiptRepo receiptRepo = (opm == null)? new LocalReceiptRepo(local) : new OpmReceiptRepo(opm);
        WorklogRepo worklogRepo = (opm == null)? new LocalWorklogRepo(local) : new OpmWorklogRepo(opm);
        WorklogTypeRepo worklogTypeRepo = (opm == null)? new LocalWorklogTypeRepo(local) : new OpmWorklogTypeRepo(opm);
        ProjectRepo projectRepo = (opm == null)? new LocalProjectRepo(local) : new OpmProjectRepo(opm);

        UserService userService = new UserService(userRepo, sessionConfig.salt);
        SessionService sessionService = new SessionService(sessionConfig, sessionRepo, userRepo);
        PermissionService permissionService = new PermissionService(permissionRepo);
        RoleService roleService = new RoleService(roleRepo);
        AssignmentService assignmentService = new AssignmentService(assignmentRepo);
        EmployeeService employeeService = new EmployeeService(employeeRepo);
        VacationService vacationService = new VacationService(vacationRepo);
        ExpenseService expenseService = new ExpenseService(expenseRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);
        WorklogService worklogService = new WorklogService(worklogRepo);
        WorklogTypeService worklogTypeService = new WorklogTypeService(worklogTypeRepo);
        ProjectService projectService = new ProjectService(projectRepo);

        RestServer server = new RestServer(restConfig);
        server.enrol(new UserController(userService));
        server.enrol(new SessionController(sessionService));
        server.enrol(new PermissionController(permissionService));
        server.enrol(new RoleController(roleService));
        server.enrol(new AssignmentController(assignmentService));
        server.enrol(new EmployeeController(employeeService));
        server.enrol(new VacationController(vacationService));
        server.enrol(new ExpenseController(expenseService));
        server.enrol(new ReceiptController(receiptService));
        server.enrol(new WorklogController(worklogService));
        server.enrol(new WorklogTypeController(worklogTypeService));
        server.enrol(new ProjectController(projectService));
        server.run();
    }
}
