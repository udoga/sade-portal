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
        this.loadRelations(this.assignmentApi, this.assignments, "detailed");
    }

    loadGrants = function () {
        this.loadRelations(this.grantApi, this.grants, "detailed");
    }

    removeAssignment = function (id) {
        this.removeRelation(this.assignmentApi, this.assignments, id);
    }

    removeGrant = function (id) {
        this.removeRelation(this.grantApi, this.grants, id);
    }
}
