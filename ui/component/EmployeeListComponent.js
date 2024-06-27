class EmployeeListComponent extends ListComponent {
    employees = this.records;
    users = [];

    constructor(api, notifier) {
        super(api, notifier, "employees");
    }

    loadAll = function (userClient) {
        this.load();
        userClient.list().then(json => json.data ? Object.assign(this.users, json.data) : this.notifyError(json));
    }

    getUsername = function (userId) {
        for (let u of this.users) if (u.id === userId) return u.username;
        return "-";
    }
}
