class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];

    constructor(api, notifier) {
        super(api, notifier);
    }

    loadAll = function (clientApi) {
        this.load("detailed");
        this.loadOptions(clientApi, this.clients);
    }
}
