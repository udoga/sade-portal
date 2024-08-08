class ClientListComponent extends ListComponent {
    clients = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier, "clients");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
