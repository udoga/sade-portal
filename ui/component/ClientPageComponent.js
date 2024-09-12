class ClientPageComponent extends PageComponent {
    client = this.record;
    projects = [];

    constructor(router, api, notifier, projectApi) {
        super(router, api, notifier, "client", "detailed");
        this.projectApi = projectApi;
    }

    afterLoad = function () {
        this.loadProjects();
    }

    loadProjects = function (reload = false) {
        this.loadRelations("projects", this.projectApi, this.projects, reload, "detailed");
    }

    removeProject = function (id) {
        this.removeRelation(this.projectApi, this.projects, id);
    }

    getSector = function () {
        return this.client.sector ? this.client.sector : {};
    }
}
