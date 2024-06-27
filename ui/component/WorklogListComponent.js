class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    activities = [];
    projects = [];

    constructor(api, notifier) {
        super(api, notifier, "worklogs");
    }

    loadAll = function (employeeApi, activityApi, projectApi) {
        this.load();
        this.fillArray(employeeApi, this.employees);
        this.fillArray(activityApi, this.activities);
        this.fillArray(projectApi, this.projects);
    }

    getEmployeeName = function (employeeId) {
        for (let e of this.employees) if (e.id === employeeId) return (e.firstName + " " + e.lastName);
        return "-";
    }

    getActivityName = function (activityId) {
        for (let a of this.activities) if (a.id === activityId) return (a.name);
        return "-";
    }

    getProjectName = function (projectId) {
        for (let p of this.projects) if (p.id === projectId) return (p.name);
        return "-";
    }

    fillArray = function (api, array) {
        api.list().then(json => json.data ? Object.assign(array, json.data) : this.notifyError(json));
    }
}
