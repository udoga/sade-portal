class ClientListComponent extends ListComponent {
    clients = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier, "detailed");
    }
}
