class GrantFormComponent extends FormComponent {
    grant = this.record;
    roles = [];
    permissions = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (roleApi, permissionApi) {
        this.load();
        this.loadOptions(roleApi, this.roles);
        this.loadOptions(permissionApi, this.permissions);
    }
}
