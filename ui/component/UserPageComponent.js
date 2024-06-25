class UserPageComponent extends PageComponent {
    user = this.record;
    roles = [];

    constructor(router, client, notifier) {
        super(router, client, notifier, "user");
    }

    loadAll = function (roleClient) {
        this.load();
        roleClient.listByUserId(this.router.getId())
            .then(json => json.data ? Object.assign(this.roles, json.data) : this.notifyError(json));
    }

    getRoleNames = function () {
        return this.roles.map(r => r.name).join(', ');
    }
}
