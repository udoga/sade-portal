class EmployeeFormComponent extends FormComponent {
    employee = this.record;
    users = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "employee");
    }

    loadAll = function (userApi) {
        this.load();
        userApi.list().then(json => json.data ? Object.assign(this.users, json.data) : this.notifyError(json));
    }
}
