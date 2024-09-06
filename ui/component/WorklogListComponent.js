class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    projects = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
        this.query.order = "newest";
    }

    loadAll = function (employeeApi, projectApi) {
        this.load("detailed");
        this.loadOptions(employeeApi, this.employees);
        this.loadOptions(projectApi, this.projects);
    }
}
