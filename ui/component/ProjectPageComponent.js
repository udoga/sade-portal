class ProjectPageComponent extends PageComponent {
    project = this.record;
    worklogs = [];

    constructor(router, api, notifier, worklogApi) {
        super(router, api, notifier, "project");
        this.worklogApi = worklogApi;
    }

    afterLoad = function () {
        this.loadWorklogs();
        this.selectTab("client");
    }

    loadWorklogs = function () {
        this.loadRelations(this.worklogApi, this.worklogs, "detailed");
    }

    removeWorklog = function (id) {
        this.removeRelation(this.worklogApi, this.worklogs, id);
    }

    getClient = function () {
        return this.project.client ? this.project.client : {};
    }
}
