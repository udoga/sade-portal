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
        this.loadRelations(this.assignmentApi, this.assignments, "detailed");
    }

    loadEmployee = function () {
        this.loadRelation(this.employeeApi, this.employee);
    }

    removeAssignment = function (id) {
        this.removeRelation(this.assignmentApi, this.assignments, id);
    }
}
