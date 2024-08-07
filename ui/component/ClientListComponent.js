class ClientListComponent extends ListComponent {
    clients = this.records;

    constructor(api, notifier) {
        super(api, notifier, "clients");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
