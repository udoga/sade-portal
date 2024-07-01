var notifier = new Notifier();
var httpClient = new HttpClient();
var router = new Router(notifier, httpClient);

var userApi = new UserApi();
var sessionApi = new SessionApi();
var roleApi = new RoleApi();
var permissionApi = new PermissionApi();
var assignmentApi = new AssignmentApi();
var grantApi = new GrantApi();
var employeeApi = new EmployeeApi();
var clientApi = new ClientApi();
var projectApi = new ProjectApi();
var activityApi = new ActivityApi();
var worklogApi = new WorklogApi();
var vacationApi = new VacationApi();
var expenseApi = new ExpenseApi();
