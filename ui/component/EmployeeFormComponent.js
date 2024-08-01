class EmployeeFormComponent extends FormComponent {
    employee = this.record;
    users = [];
    username = "";
    userApi = null;

    constructor(router, api, notifier, userApi) {
        super(router, api, notifier, "employee");
        this.userApi = userApi;
    }

    loadRelations = function () {
        this.userApi.list().then(json => json.data ? this.loadUsersAndUsername(json.data) : this.notifyError(json));
    }

    loadUsersAndUsername = function (users) {
        Object.assign(this.users, users);
        if (this.employee.userId)
            this.username = users.find(user => user.id === this.employee.userId).username;
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
