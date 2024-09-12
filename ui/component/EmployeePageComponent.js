class EmployeePageComponent extends PageComponent {
    employee = this.record;
    vacations = [];
    expenses = [];
    worklogs = [];
    engagements = [];

    constructor(router, api, notifier, engagementApi, worklogApi, vacationApi, expenseApi) {
        super(router, api, notifier, "employee", "detailed");
        this.engagementApi = engagementApi;
        this.worklogApi = worklogApi;
        this.vacationApi = vacationApi;
        this.expenseApi = expenseApi;
    }

    afterLoad = function () {
        this.loadEngagements();
    }

    loadEngagements = function (reload = false) {
        this.loadRelations("engagements", this.engagementApi, this.engagements, reload, "detailed");
    }

    loadWorklogs = function (reload = false) {
        this.loadRelations("worklogs", this.worklogApi, this.worklogs, reload, "detailed", {order: "newest"});
    }

    loadVacations = function (reload = false) {
        this.loadRelations("vacations", this.vacationApi, this.vacations, reload, "detailed");
    }

    loadExpenses = function (reload = false) {
        this.loadRelations("expenses", this.expenseApi, this.expenses, reload, "detailed");
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
