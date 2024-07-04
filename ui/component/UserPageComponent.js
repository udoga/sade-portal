class UserPageComponent extends PageComponent {
    user = this.record;
    assignments = [];
    employee = {};
    employeeApi = null;
    assignmentApi = null;

    constructor(router, api, notifier, employeeApi, assignmentApi) {
        super(router, api, notifier, "user");
        this.assignmentApi = assignmentApi;
        this.employeeApi = employeeApi;
    }

    loadRelations = function () {
        this.selectTab("assignments");
    }

    loadTab = function (tab) {
        if (tab == "assignments") this.loadAssignments();
        else if (tab == "employee") this.loadEmployee();
    }

    loadAssignments = function () {
        this.assignmentApi.listWithRoleByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.assignments, json.data) : this.notifyError(json));
    }

    loadEmployee = function () {
        this.employeeApi.listByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.employee, json.data[0]) : this.notifyError(json));
    }

    removeAssignment = function (id) {
        this.assignmentApi.delete(id)
            .then(json => json.data ? this.removeFromTable(this.assignments, json) : this.notifyError(json));
    }
}
