class UserPageComponent extends PageComponent {
    user = this.record;
    assignmentApi = null;
    employeeApi = null;
    assignments = [];
    employee = {};

    constructor(router, api, notifier, assignmentApi, employeeApi) {
        super(router, api, notifier, "user");
        this.assignmentApi = assignmentApi;
        this.employeeApi = employeeApi;
    }

    afterLoad = function () {
        this.loadAssignments();
        this.loadEmployee();
        this.selectTab("assignments");
    }

    loadAssignments = function () {
        this.assignmentApi.list("detailed", { userId: this.router.getId() })
            .then(json => json.data ? Object.assign(this.assignments, json.data) : this.notifyError(json));
    }

    loadEmployee = function () {
        this.employeeApi.list("", { userId: this.router.getId() })
            .then(json => json.data ? Object.assign(this.employee, json.data[0]) : this.notifyError(json));
    }

    removeAssignment = function (id) {
        if (confirm("Are you sure?"))
            this.assignmentApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.assignments, id) : this.notifyError(json));
    }
}
