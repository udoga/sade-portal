class GrantFormComponent extends FormComponent {
    grant = this.record;
    roles = [];
    permissions = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "grant");
    }

    loadAll = function (roleApi, permissionApi) {
        this.load();
        this.loadSummarized(roleApi, this.roles);
        this.loadSummarized(permissionApi, this.permissions);
    }

    routeOnSuccess = function () {
        this.router.route("#/role-page/" + this.grant.roleId);
    }
}
