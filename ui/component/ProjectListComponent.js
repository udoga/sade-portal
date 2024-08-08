class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];

    constructor(api, notifier) {
        super(api, notifier, "projects");
    }

    loadAll = function (clientApi) {
        this.load("detailed");
        this.loadClients(clientApi);
    }

    loadClients = function (clientApi) {
        clientApi.listSummarized()
            .then(json => json.data? Object.assign(this.clients, json.data) : this.notifyError(json.error));
    }
}
