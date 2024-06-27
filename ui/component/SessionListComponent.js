class SessionListComponent extends ListComponent {
    sessions = this.records;
    users = [];

    constructor(api, notifier) {
        super(api, notifier, "sessions");
    }

    loadAll = function (userApi) {
        this.load();
        userApi.list().then(json => json.data ? Object.assign(this.users, json.data) : this.notifyError(json));
    }

    getUsername = function (userId) {
        for (let u of this.users) if (u.id === userId) return u.username;
        return "-";
    }
}
