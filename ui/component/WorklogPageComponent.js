class WorklogPageComponent extends PageComponent {
    worklog = this.record;
    project = {};
    employee = {};
    projectApi = null;
    employeeApi = null;

    constructor(router, api, notifier, projectApi, employeeApi) {
        super(router, api, notifier, "worklog");
        this.projectApi = projectApi;
        this.employeeApi = employeeApi;
    }

    loadRelations = function () {
        this.selectTab("project");
    }

    loadTab = function (tab) {
        if (tab == "project") this.loadProject();
        else if (tab == "employee") this.loadEmployee();
    }

    loadProject = function () {
        this.projectApi.read(this.worklog.projectId)
            .then(json => json.data ? Object.assign(this.project, json.data) : this.notifyError(json));
    }

    loadEmployee = function () {
        this.employeeApi.read(this.worklog.employeeId)
            .then(json => json.data ? Object.assign(this.employee, json.data) : this.notifyError(json));
    }
}
