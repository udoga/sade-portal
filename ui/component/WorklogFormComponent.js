class WorklogFormComponent extends FormComponent {
    worklog = this.record;
    projects = [];
    employees = [];
    activities = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (projectApi, employeeApi, activityApi) {
        this.load();
        this.loadOptions(projectApi, this.projects);
        this.loadOptions(employeeApi, this.employees);
        this.loadOptions(activityApi, this.activities);
    }
}
