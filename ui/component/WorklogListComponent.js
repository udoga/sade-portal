class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    types = [];
    projects = [];

    constructor(client, notifier) {
        super(client, notifier, "worklogs");
    }

    loadAll = function (employeeClient, typeClient, projectClient) {
        this.load();
        this.fillArray(employeeClient, this.employees);
        this.fillArray(typeClient, this.types);
        this.fillArray(projectClient, this.projects);
    }

    getEmployeeName = function (employeeId) {
        for (let e of this.employees) if (e.id === employeeId) return (e.firstName + " " + e.lastName);
        return "-";
    }

    getTypeName = function (typeId) {
        for (let t of this.types) if (t.id === typeId) return (t.name);
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
