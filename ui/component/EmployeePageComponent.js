class EmployeePageComponent extends PageComponent {
    employee = this.record;
    vacations = [];
    expenses = [];
    worklogs = [];
    engagements = [];

    constructor(router, api, notifier, vacationApi, expenseApi, worklogApi, engagementApi) {
        super(router, api, notifier, "employee");
        this.vacationApi = vacationApi;
        this.expenseApi = expenseApi;
        this.worklogApi = worklogApi;
        this.engagementApi = engagementApi;
    }

    afterLoad = function () {
        this.loadVacations();
        this.loadExpenses();
        this.loadWorklogs();
        this.loadEngagements();
        this.selectTab("user");
    }

    loadVacations = function () {
        this.loadRelations(this.vacationApi, this.vacations, "detailed");
    }

    loadExpenses = function () {
        this.loadRelations(this.expenseApi, this.expenses, "detailed");
    }

    loadWorklogs = function () {
        this.loadRelations(this.worklogApi, this.worklogs, "detailed");
    }

    loadEngagements = function () {
        this.loadRelations(this.engagementApi, this.engagements, "detailed");
    }

    removeVacation = function (id) {
        this.removeRelation(this.vacationApi, this.vacations, id);
    }

    removeExpense = function (id) {
        this.removeRelation(this.expenseApi, this.expenses, id);
    }

    removeWorklog = function (id) {
        this.removeRelation(this.worklogApi, this.worklogs, id);
    }

    removeEngagement = function (id) {
        this.removeRelation(this.engagementApi, this.engagements, id);
    }

    getUser = function () {
        return this.employee.user ? this.employee.user : {};
    }
}
