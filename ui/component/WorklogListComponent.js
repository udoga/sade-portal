class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    projects = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "detailed");
        this.query.order = "newest";
    }

    loadAll = function (employeeApi, projectApi) {
        this.load();
        this.loadOptions(employeeApi, this.employees);
        this.loadOptions(projectApi, this.projects);
    }
}
