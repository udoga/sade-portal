class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];
    query = {};

    constructor(api, notifier) {
        super(api, notifier, "projects");
    }

    loadAll = function (clientApi) {
        this.loadProjects();
        this.loadClients(clientApi);
    }

    loadProjects = function () {
        this.projects.length = 0;
        this.api.listDetailed(this.getParams()).then(json => this.handleLoadResponse(json));
    }

    loadClients = function (clientApi) {
        clientApi.listSummarized()
            .then(json => json.data? Object.assign(this.clients, json.data) : this.notifyError(json.error));
    }

    getParams = function() {
      return Object.fromEntries(Object.entries(this.query).filter(([_, v]) => v != null));
    }
}
