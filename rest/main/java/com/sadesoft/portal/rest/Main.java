package com.sadesoft.portal.rest;

import java.sql.Connection;
import com.sadesoft.portal.base.repo.JdbcConnector;
import com.sadesoft.portal.base.model.Props;
import com.sadesoft.portal.base.rest.RestConfig;
import com.sadesoft.portal.core.mock.MockSessionRepo;
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
        Connection c = new JdbcConnector(props.getSection(props.getString("repo"))).connect();
        boolean isLocalRepo = !props.getString("repo").equals("opm");

        UserRepo userRepo = isLocalRepo? new LocalUserRepo(c) : new OpmUserRepo(c);
        SessionRepo sessionRepo = isLocalRepo? new LocalSessionRepo(c) : new MockSessionRepo();
        RoleRepo roleRepo = isLocalRepo? new LocalRoleRepo(c) : new OpmRoleRepo(c);
        PermissionRepo permissionRepo = isLocalRepo? new LocalPermissionRepo(c) : new OpmPermissionRepo(c);
        AssignmentRepo assignmentRepo = isLocalRepo? new LocalAssignmentRepo(c) : new OpmAssignmentRepo(c);
        GrantRepo grantRepo = isLocalRepo? new LocalGrantRepo(c) : new OpmGrantRepo(c);
        EmployeeRepo employeeRepo = isLocalRepo? new LocalEmployeeRepo(c) : new OpmEmployeeRepo(c);
        SectorRepo sectorRepo = isLocalRepo? new LocalSectorRepo(c) : new OpmSectorRepo(c);
        ClientRepo clientRepo = isLocalRepo? new LocalClientRepo(c) : new OpmClientRepo(c);
        ProjectRepo projectRepo = isLocalRepo? new LocalProjectRepo(c) : new OpmProjectRepo(c);
        ActivityRepo activityRepo = isLocalRepo? new LocalActivityRepo(c) : new OpmActivityRepo(c);
        WorklogRepo worklogRepo = isLocalRepo? new LocalWorklogRepo(c) : new OpmWorklogRepo(c);
        VacationCategoryRepo vacationCategoryRepo = isLocalRepo? new LocalVacationCategoryRepo(c) : new OpmVacationCategoryRepo(c);
        VacationRepo vacationRepo = isLocalRepo? new LocalVacationRepo(c) : new OpmVacationRepo(c);
        ExpenseRepo expenseRepo = isLocalRepo? new LocalExpenseRepo(c) : new OpmExpenseRepo(c);
        ReceiptCategoryRepo receiptCategoryRepo = isLocalRepo? new LocalReceiptCategoryRepo(c) : new OpmReceiptCategoryRepo(c);
        ReceiptRepo receiptRepo = isLocalRepo? new LocalReceiptRepo(c) : new OpmReceiptRepo(c);

        UserService userService = new UserService(userRepo, sessionConfig.salt);
        SessionService sessionService = new SessionService(sessionConfig, sessionRepo, userRepo);
        RoleService roleService = new RoleService(roleRepo);
        PermissionService permissionService = new PermissionService(permissionRepo);
        AssignmentService assignmentService = new AssignmentService(assignmentRepo);
        GrantService grantService = new GrantService(grantRepo);
        EmployeeService employeeService = new EmployeeService(employeeRepo);
        SectorService sectorService = new SectorService(sectorRepo);
        ClientService clientService = new ClientService(clientRepo);
        ProjectService projectService = new ProjectService(projectRepo);
        ActivityService activityService = new ActivityService(activityRepo);
        WorklogService worklogService = new WorklogService(worklogRepo);
        VacationCategoryService vacationCategoryService = new VacationCategoryService(vacationCategoryRepo);
        VacationService vacationService = new VacationService(vacationRepo);
        ExpenseService expenseService = new ExpenseService(expenseRepo);
        ReceiptCategoryService receiptCategoryService = new ReceiptCategoryService(receiptCategoryRepo);
        ReceiptService receiptService = new ReceiptService(receiptRepo);

        RestServer server = new RestServer(restConfig);
        server.enrol(new UserController(userService));
        server.enrol(new SessionController(sessionService));
        server.enrol(new RoleController(roleService));
        server.enrol(new PermissionController(permissionService));
        server.enrol(new AssignmentController(assignmentService));
        server.enrol(new GrantController(grantService));
        server.enrol(new EmployeeController(employeeService));
        server.enrol(new SectorController(sectorService));
        server.enrol(new ClientController(clientService));
        server.enrol(new ProjectController(projectService));
        server.enrol(new ActivityController(activityService));
        server.enrol(new WorklogController(worklogService));
        server.enrol(new VacationCategoryController(vacationCategoryService));
        server.enrol(new VacationController(vacationService));
        server.enrol(new ExpenseController(expenseService));
        server.enrol(new ReceiptCategoryController(receiptCategoryService));
        server.enrol(new ReceiptController(receiptService));
        server.run();
    }
}
