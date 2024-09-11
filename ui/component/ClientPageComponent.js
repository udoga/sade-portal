class ClientPageComponent extends PageComponent {
    client = this.record;
    projects = [];

    constructor(router, api, notifier, projectApi) {
        super(router, api, notifier, "client", "detailed");
        this.projectApi = projectApi
    }

    afterLoad = function () {
        this.loadProjects();
        this.selectTab("projects");
    }

    loadProjects = function () {
        this.loadRelations(this.projectApi, this.projects, "detailed");
    }

    removeProject = function (id) {
        this.removeRelation(this.projectApi, this.projects, id);
    }

    getSector = function () {
        return this.client.sector ? this.client.sector : {};
    }
}
