class EmployeePageComponent extends PageComponent {
    employee = this.record;
    vacations = [];
    expenses = [];
    worklogs = [];

    constructor(router, api, notifier, vacationApi, expenseApi, worklogApi) {
        super(router, api, notifier, "employee");
        this.vacationApi = vacationApi;
        this.expenseApi = expenseApi;
        this.worklogApi = worklogApi;
    }

    afterLoad = function () {
        this.loadVacations();
        this.loadExpenses();
        this.loadWorklogs();
        this.selectTab("user");
    }

    loadVacations = function () {
        this.vacationApi.list("detailed", { employeeId: this.employee.id })
            .then(json => json.data ? Object.assign(this.vacations, json.data) : this.notifyError(json));
    }

    loadExpenses = function () {
        this.expenseApi.list("detailed", { employeeId: this.employee.id })
            .then(json => json.data ? Object.assign(this.expenses, json.data) : this.notifyError(json));
    }

    loadWorklogs = function () {
        this.worklogApi.list("detailed", { employeeId: this.employee.id, offset: this.worklogs.length })
            .then(json => json.data ? this.worklogs.push(...json.data) : this.notifyError(json));
    }

    removeVacation = function (id) {
        if (confirm("Are you sure?"))
            this.vacationApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.vacations, json) : this.notifyError(json));
    }

    removeExpense = function (id) {
        if (confirm("Are you sure?"))
            this.expenseApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.expenses, json) : this.notifyError(json));
    }

    removeWorklog = function (id) {
        if (confirm("Are you sure?"))
            this.worklogApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.worklogs, json) : this.notifyError(json));
    }

    getUser = function () {
        return this.employee.user ? this.employee.user : {};
    }

    hasMoreWorklogs = function () {
        return this.worklogs.length % 100 == 0;
    }
}
