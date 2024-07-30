class EmployeeFormComponent extends FormComponent {
    employee = this.record;
    users = [];
    username = "";

    constructor(router, api, notifier) {
        super(router, api, notifier, "employee");
    }

    loadAll = function (userApi) {
        this.load();
        userApi.list().then(json => json.data ? Object.assign(this.users, json.data) : this.notifyError(json));
    }

    getFilteredUsers = function () {
        var input = this.username.toLowerCase();
        return this.users.filter(u => input && u.username !== input && u.username.toLowerCase().includes(input));
    }

    saveEmployee = function () {
        var user = this.users.find(user => user.username === this.username);
        this.employee.userId = user? user.id : null;
        this.save();
    }
}
