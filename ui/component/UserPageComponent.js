class UserPageComponent extends PageComponent {
    user = this.record;
    employee = {};
    roles = [];
    selectedTitle = "";
    employeeApi = null;
    roleApi = null;

    constructor(router, api, notifier, employeeApi, roleApi) {
        super(router, api, notifier, "user");
        this.employeeApi = employeeApi;
        this.roleApi = roleApi;
    }

    select = function (title) {
        this.selectedTitle = title;
        if (title == "employee") this.loadEmployee();
        else if (title == "roles") this.loadRoles();
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

    loadRoles = function () {
        this.roleApi.listByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.roles, json.data) : this.notifyError(json));
    }

    isEmployee = function () {
        return Object.keys(this.employee).length !== 0;
    }
}
