class WorklogFormComponent extends FormComponent {
    worklog = this.record;
    projects = [];
    employees = [];
    activities = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "worklog");
    }

    loadAll = function (projectApi, employeeApi, activityApi) {
        this.load();
        this.loadSummarized(projectApi, this.projects);
        this.loadSummarized(employeeApi, this.employees);
        this.loadSummarized(activityApi, this.activities);
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
