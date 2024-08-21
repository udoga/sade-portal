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
        employeeApi.listSummarized()
            .then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json.error));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
