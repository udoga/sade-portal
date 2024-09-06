class AssignmentFormComponent extends FormComponent {
    assignment = this.record;
    users = [];
    roles = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "assignment");
    }

    loadAll = function (userApi, roleApi) {
        this.load();
        this.loadOptions(userApi, this.users);
        this.loadOptions(roleApi, this.roles);
    }

    routeOnSuccess = function () {
        this.router.route("#/user-page/" + this.assignment.userId);
    }
}
