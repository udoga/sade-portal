var formatter = new Formatter();
var httpClient = new HttpClient();
var notifier = new Notifier();
var router = new Router(notifier, httpClient);

var userApi = new UserApi();
var sessionApi = new SessionApi();
var roleApi = new RoleApi();
var permissionApi = new PermissionApi();
var assignmentApi = new AssignmentApi();
var grantApi = new GrantApi();
var employeeApi = new EmployeeApi();
var positionApi = new PositionApi();
var engagementApi = new EngagementApi();
var sectorApi = new SectorApi();
var clientApi = new ClientApi();
var projectApi = new ProjectApi();
var activityApi = new ActivityApi();
var worklogApi = new WorklogApi();
var vacationCategoryApi = new VacationCategoryApi();
var vacationApi = new VacationApi();
var expenseApi = new ExpenseApi();
var receiptCategoryApi = new ReceiptCategoryApi();
var receiptApi = new ReceiptApi();

initDatePicker = function (el) {
    flatpickr(el, {
        dateFormat: 'Y-m-d',
        onChange: () => el.dispatchEvent(new Event('input'))
    });
}
