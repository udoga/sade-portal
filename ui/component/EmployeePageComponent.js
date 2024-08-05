class EmployeePageComponent extends PageComponent {
    employee = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "employee");
    }

    loadRelations = function () {
        this.selectTab("user");
    }

    getUser = function () {
        return this.employee.user ? this.employee.user : {};
    }
}
