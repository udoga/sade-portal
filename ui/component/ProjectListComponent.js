class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];
    clientApi = null;

    constructor(router, api, notifier, clientApi) {
        super(router, api, notifier, "detailed");
        this.clientApi = clientApi;
    }

    afterLoad = function () {
        this.loadOptions(this.clientApi, this.clients);
    }
}
