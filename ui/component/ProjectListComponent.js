class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];

    constructor(api, notifier) {
        super(api, notifier, "projects");
    }

    load = function () {
        this.api.listWithClient().then(json => this.handleLoadResponse(json));
    }

    getClientName = function (clientId) {
        for (let c of this.clients) if (c.id === clientId) return (c.name);
        return "-";
    }
}
