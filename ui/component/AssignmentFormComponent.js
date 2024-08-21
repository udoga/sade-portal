class AssignmentFormComponent extends FormComponent {
    assignment = this.record;
    users = [];
    roles = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "assignment");
    }

    loadAll = function (userApi, roleApi) {
        this.load();
        this.loadUsers(userApi);
        this.loadRoles(roleApi);
    }

    loadUsers = function (userApi) {
        userApi.listSummarized()
            .then(json => json.data? Object.assign(this.users, json.data) : this.notifyError(json));
    }

    loadRoles = function (roleApi) {
        roleApi.listSummarized()
            .then(json => json.data? Object.assign(this.roles, json.data) : this.notifyError(json));
    }

    routeOnSuccess = function () {
        this.router.route("#/user-page/" + this.assignment.userId);
    }
}
