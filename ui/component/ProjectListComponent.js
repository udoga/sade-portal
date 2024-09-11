class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "detailed");
    }

    loadAll = function (clientApi) {
        this.load();
        this.loadOptions(clientApi, this.clients);
    }
}
