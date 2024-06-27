class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];

    constructor(api, notifier) {
        super(api, notifier, "projects");
    }

    loadAll = function (clientApi) {
        this.load();
        clientApi.list().then(json => json.data ? Object.assign(this.clients, json.data) : this.notifyError(json));
    }

    getClientName = function (clientId) {
        for (let c of this.clients) if (c.id === clientId) return (c.name);
        return "-";
    }
}
