class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    types = [];
    projects = [];

    constructor(client, notifier) {
      super(client, notifier, "worklogs");
    }

    loadAll = function(employeeClient, typeClient, projectClient) {
      this.load();
      employeeClient.list().then(json => json.data? Object.assign(this.employees, json.data) : this.notifyError(json));
      typeClient.list().then(json => json.data? Object.assign(this.types, json.data) : this.notifyError(json));
      projectClient.list().then(json => json.data? Object.assign(this.projects, json.data) : this.notifyError(json));
    }

    getEmployeeName = function(employeeId) {
        for (let e of this.employees) if (e.id === employeeId) return (e.firstName + " " + e.lastName);
        return "-";
      }

    getTypeName = function(typeId) {
        for (let t of this.types) if (t.id === typeId) return (t.name);
        return "-";
      }

    getProjectName = function(projectId) {
        for (let p of this.projects) if (p.id === projectId) return (p.name);
        return "-";
      }
  }
