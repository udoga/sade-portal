class GrantFormComponent extends FormComponent {
    grant = this.record;
    roles = [];
    permissions = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "grant");
    }

    loadAll = function (roleApi, permissionApi) {
        this.load();
        this.loadRoles(roleApi);
        this.loadPermissions(permissionApi);
    }

    loadRoles = function (roleApi) {
        roleApi.listSummarized()
            .then(json => json.data? Object.assign(this.roles, json.data) : this.notifyError(json));
    }

    loadPermissions = function (permissionApi) {
        permissionApi.listSummarized()
            .then(json => json.data? Object.assign(this.permissions, json.data) : this.notifyError(json));
    }

    routeOnSuccess = function () {
        this.router.route("#/role-page/" + this.grant.roleId);
    }
}
