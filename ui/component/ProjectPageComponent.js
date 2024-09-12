class ProjectPageComponent extends PageComponent {
    project = this.record;
    engagements = [];
    worklogs = [];

    constructor(router, api, notifier, engagementApi, worklogApi) {
        super(router, api, notifier, "project", "detailed");
        this.engagementApi = engagementApi;
        this.worklogApi = worklogApi;
    }

    afterLoad = function () {
        this.loadEngagements();
    }

    loadEngagements = function (reload = false) {
        this.loadRelations("engagements", this.engagementApi, this.engagements, reload, "detailed");
    }

    loadWorklogs = function (reload = false) {
        this.loadRelations("worklogs", this.worklogApi, this.worklogs, reload, "detailed", {order: "newest"});
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
