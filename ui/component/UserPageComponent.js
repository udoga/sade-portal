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
    }

    loadAssignments = function (reload = false) {
        this.loadRelations("assignments", this.assignmentApi, this.assignments, reload, "detailed");
    }

    loadEmployee = function () {
        this.loadRelation("employee", this.employeeApi, this.employee);
    }

    removeAssignment = function (id) {
        this.removeRelation(this.assignmentApi, this.assignments, id);
    }
}
