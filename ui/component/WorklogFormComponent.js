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
        projectApi.list().then(json => json.data? Object.assign(this.projects, json.data) : this.notifyError(json));
        employeeApi.list().then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json));
        activityApi.list().then(json => json.data? Object.assign(this.activities, json.data) : this.notifyError(json));
    }

    getFullName = function (employee) {
        return employee.firstName + " " + employee.lastName;
    }
}
