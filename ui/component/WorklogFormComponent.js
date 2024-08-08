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
        this.loadProject(projectApi);
        this.loadEmployee(employeeApi);
        this.loadActivity(activityApi);
    }

    loadProject = function (projectApi) {
        projectApi.listSummarized()
            .then(json => json.data? Object.assign(this.projects, json.data) : this.notifyError(json));
    }

    loadEmployee = function (employeeApi) {
        employeeApi.listSummarized()
            .then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json));
    }

    loadActivity = function (activityApi) {
        activityApi.listSummarized()
            .then(json => json.data? Object.assign(this.activities, json.data) : this.notifyError(json));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
