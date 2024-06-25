class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    activities = [];
    projects = [];

    constructor(client, notifier) {
        super(client, notifier, "worklogs");
    }

    loadAll = function (employeeClient, activityClient, projectClient) {
        this.load();
        this.fillArray(employeeClient, this.employees);
        this.fillArray(activityClient, this.activities);
        this.fillArray(projectClient, this.projects);
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

    fillArray = function (client, array) {
        client.list().then(json => json.data ? Object.assign(array, json.data) : this.notifyError(json));
    }
}
