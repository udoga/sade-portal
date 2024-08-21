class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    projects = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (employeeApi, projectApi) {
        this.load("detailed");
        this.loadSummarized(employeeApi, this.employees);
        this.loadSummarized(projectApi, this.projects);
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
