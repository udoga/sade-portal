class AssignmentFormComponent extends FormComponent {
    assignment = this.record;
    users = [];
    roles = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "assignment");
    }

    loadAll = function (userApi, roleApi) {
        this.load();
        userApi.list().then(json => json.data? Object.assign(this.users, json.data) : this.notifyError(json));
        roleApi.list().then(json => json.data? Object.assign(this.roles, json.data) : this.notifyError(json));
    }
}
