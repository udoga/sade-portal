class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];
    query = {};

    constructor(api, notifier) {
        super(api, notifier, "projects");
    }

    loadAll = function (clientApi) {
        this.loadProjects();
        clientApi.list().then(json => json.data? Object.assign(this.clients, json.data) : this.notifyError(json.error));
    }

    loadProjects = function () {
        this.projects.length = 0;
        if (!this.query.clientId) delete this.query.clientId;
        this.api.listDetailed(this.query).then(json => this.handleLoadResponse(json));
    }
}
