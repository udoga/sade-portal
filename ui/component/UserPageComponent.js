class UserPageComponent extends PageComponent {
    user = this.record;
    employee = {};
    roles = [];
    selectedTitle = "";
    employeeClient = null;
    roleClient = null;

    constructor(router, client, notifier, employeeClient, roleClient) {
        super(router, client, notifier, "user");
        this.employeeClient = employeeClient;
        this.roleClient = roleClient;
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
        this.employeeClient.listByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.employee, json.data[0]) : this.notifyError(json));
    }

    loadRoles = function () {
        this.roleClient.listByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.roles, json.data) : this.notifyError(json));
    }

    isEmployee = function () {
        return Object.keys(this.employee).length !== 0;
    }
}
