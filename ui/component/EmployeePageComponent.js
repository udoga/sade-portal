class EmployeePageComponent extends PageComponent {
    employee = this.record;
    vacations = [];
    expenses = [];
    worklogs = [];
    engagements = [];

    constructor(router, api, notifier, engagementApi, worklogApi, vacationApi, expenseApi) {
        super(router, api, notifier, "employee");
        this.engagementApi = engagementApi;
        this.worklogApi = worklogApi;
        this.vacationApi = vacationApi;
        this.expenseApi = expenseApi;
    }

    afterLoad = function () {
        this.loadEngagements();
        this.loadWorklogs();
        this.loadVacations();
        this.loadExpenses();
        this.selectTab("user");
    }

    loadEngagements = function () {
        this.loadRelations(this.engagementApi, this.engagements, "detailed");
    }

    loadWorklogs = function () {
        this.loadRelations(this.worklogApi, this.worklogs, "detailed", {order: "newest"});
    }

    loadVacations = function () {
        this.loadRelations(this.vacationApi, this.vacations, "detailed");
    }

    loadExpenses = function () {
        this.loadRelations(this.expenseApi, this.expenses, "detailed");
    }

    removeEngagement = function (id) {
        this.removeRelation(this.engagementApi, this.engagements, id);
    }

    removeWorklog = function (id) {
        this.removeRelation(this.worklogApi, this.worklogs, id);
    }

    removeVacation = function (id) {
        this.removeRelation(this.vacationApi, this.vacations, id);
    }

    removeExpense = function (id) {
        this.removeRelation(this.expenseApi, this.expenses, id);
    }

    getUser = function () {
        return this.employee.user ? this.employee.user : {};
    }
}
