class ProjectPageComponent extends PageComponent {
    project = this.record;
    worklogs = [];
    engagements = [];

    constructor(router, api, notifier, worklogApi, engagementApi) {
        super(router, api, notifier, "project");
        this.worklogApi = worklogApi;
        this.engagementApi = engagementApi;
    }

    afterLoad = function () {
        this.loadWorklogs();
        this.loadEngagements();
        this.selectTab("client");
    }

    loadWorklogs = function () {
        this.loadRelations(this.worklogApi, this.worklogs, "detailed");
    }

    loadEngagements = function () {
        this.loadRelations(this.engagementApi, this.engagements, "detailed");
    }

    removeWorklog = function (id) {
        this.removeRelation(this.worklogApi, this.worklogs, id);
    }

    removeEngagement = function (id) {
        this.removeRelation(this.engagementApi, this.engagements, id);
    }

    getClient = function () {
        return this.project.client ? this.project.client : {};
    }
}
