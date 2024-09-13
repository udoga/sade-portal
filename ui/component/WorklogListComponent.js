class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    projects = [];
    employeeApi = null;
    projectApi = null;

    constructor(router, api, notifier, employeeApi, projectApi) {
        super(router, api, notifier, "detailed");
        this.employeeApi = employeeApi;
        this.projectApi = projectApi;
        this.defaultQuery = {"order": "newest"};
    }

    afterLoad = function () {
        this.loadOptions(this.employeeApi, this.employees);
        this.loadOptions(this.projectApi, this.projects);
    }
}
