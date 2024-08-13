class RolePageComponent extends PageComponent {
    role = this.record;
    assignmentApi = null;
    grantApi = null;
    assignments = [];
    grants = [];

    constructor(router, api, notifier, assignmentApi, grantApi) {
        super(router, api, notifier, "role");
        this.assignmentApi = assignmentApi;
        this.grantApi = grantApi;
    }

    afterLoad = function () {
        this.loadAssignments();
        this.loadGrants();
        this.selectTab("assignments");
    }

    loadAssignments = function () {
        this.assignmentApi.list("detailed", { roleId: this.role.id })
            .then(json => json.data ? Object.assign(this.assignments, json.data) : this.notifyError(json));
    }

    loadGrants = function (afterId = 0) {
        this.grantApi.list("detailed", { roleId: this.role.id, afterId: afterId })
            .then(json => json.data ? this.grants.push(...json.data) : this.notifyError(json));
    }

    removeAssignment = function (id) {
        if (confirm("Are you sure?"))
            this.assignmentApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.assignments, json) : this.notifyError(json));
    }

    removeGrant = function (id) {
        if (confirm("Are you sure?"))
            this.grantApi.delete(id)
                .then(json => json.data ? this.removeFromTable(this.grants, json) : this.notifyError(json));
    }

    loadMoreGrants = function () {
        this.loadGrants(this.grants.slice(-1)[0].id);
    }

    hasMoreGrants = function () {
        return this.grants.length % 100 == 0;
    }
}
