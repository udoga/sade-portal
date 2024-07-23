class EmployeeFormComponent extends FormComponent {
    employee = this.record;
    search = "";
    users = [];
    filteredUsers = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "employee");
    }

    loadAll = function (userApi) {
        this.load();
        userApi.list()
            .then(json => json.data ? Object.assign(this.users, json.data) : this.notifyError(json));
        this.filteredUsers = this.users;
    }

    filterOptions = function () {
        this.filteredUsers = this.users.filter((user) =>
            user.username.toLowerCase().includes(this.search.toLowerCase())
        );
    }

    selectUser= function (user) {
        this.employee.userId = user.id;
        this.search = user.username;
        this.filteredUsers = [];
    }
}
