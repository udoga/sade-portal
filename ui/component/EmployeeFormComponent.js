class EmployeeFormComponent extends FormComponent {
    employee = this.record;
    users = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (userApi) {
        this.load();
        this.loadOptions(userApi, this.users);
    }
}
