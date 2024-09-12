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
    }

    loadAssignments = function (reload = false) {
        this.loadRelations("assignments", this.assignmentApi, this.assignments, reload, "detailed");
    }

    loadGrants = function (reload = false) {
        this.loadRelations("grants", this.grantApi, this.grants, reload, "detailed");
    }

    removeAssignment = function (id) {
        this.removeRelation(this.assignmentApi, this.assignments, id);
    }

    removeGrant = function (id) {
        this.removeRelation(this.grantApi, this.grants, id);
    }
}
