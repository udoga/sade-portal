class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (employeeApi) {
        this.load("detailed");
        this.loadEmployees(employeeApi);
    }

    loadEmployees = function (employeeApi) {
        employeeApi.list()
            .then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json.error));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }

    loadMore = function () {
        this.query.afterId = this.worklogs.slice(-1)[0].id;
        this.load("detailed");
    }

    hasMore = function () {
        return this.worklogs.length % 100 == 0;
    }
}
