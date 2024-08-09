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
        this.loadProjects(projectApi);
        this.loadEmployees(employeeApi);
        this.loadActivities(activityApi);
    }

    loadProjects = function (projectApi) {
        projectApi.listSummarized()
            .then(json => json.data? Object.assign(this.projects, json.data) : this.notifyError(json));
    }

    loadEmployees = function (employeeApi) {
        employeeApi.listSummarized()
            .then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json));
    }

    loadActivities = function (activityApi) {
        activityApi.listSummarized()
            .then(json => json.data? Object.assign(this.activities, json.data) : this.notifyError(json));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
