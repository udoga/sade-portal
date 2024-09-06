class ProjectPageComponent extends PageComponent {
    project = this.record;
    engagements = [];
    worklogs = [];

    constructor(router, api, notifier, engagementApi, worklogApi) {
        super(router, api, notifier, "project");
        this.engagementApi = engagementApi;
        this.worklogApi = worklogApi;
    }

    afterLoad = function () {
        this.loadEngagements();
        this.loadWorklogs();
        this.selectTab("engagements");
    }

    loadEngagements = function () {
        this.loadRelations(this.engagementApi, this.engagements, "detailed");
    }

    loadWorklogs = function () {
        this.loadRelations(this.worklogApi, this.worklogs, "detailed", {order: "newest"});
    }

    removeEngagement = function (id) {
        this.removeRelation(this.engagementApi, this.engagements, id);
    }

    removeWorklog = function (id) {
        this.removeRelation(this.worklogApi, this.worklogs, id);
    }

    getClient = function () {
        return this.project.client ? this.project.client : {};
    }
}
