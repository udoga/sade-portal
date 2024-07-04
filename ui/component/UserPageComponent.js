class UserPageComponent extends PageComponent {
    user = this.record;
    assignments = [];
    employee = {};
    selectedTitle = "";
    employeeApi = null;
    assignmentApi = null;

    constructor(router, api, notifier, employeeApi, assignmentApi) {
        super(router, api, notifier, "user");
        this.assignmentApi = assignmentApi;
        this.employeeApi = employeeApi;
    }

    loadRelations = function () {
        this.select("assignments");
    }

    select = function (title) {
        this.selectedTitle = title;
        if (title == "assignments") this.loadAssignments();
        else if (title == "employee") this.loadEmployee();
    }

    isSelected = function (title) {
        return this.selectedTitle === title;
    }

    getClass = function (title) {
        return (this.selectedTitle === title)? "selected" : "";
    }

    loadEmployee = function () {
        this.employeeApi.listByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.employee, json.data[0]) : this.notifyError(json));
    }

    loadAssignments = function () {
        this.assignmentApi.listWithRoleByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.assignments, json.data) : this.notifyError(json));
    }

    isEmployee = function () {
        return Object.keys(this.employee).length !== 0;
    }

    removeAssignment = function (id) {
        this.assignmentApi.delete(id).then(json => this.handleRemoveResponse(this.assignments, json));
    }
}
