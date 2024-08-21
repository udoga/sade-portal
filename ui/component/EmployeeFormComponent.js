class EmployeeFormComponent extends FormComponent {
    employee = this.record;
    users = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "employee");
    }

    loadAll = function (userApi) {
        this.load();
        this.loadSummarized(userApi, this.users);
    }
}
