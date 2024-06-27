class ClientListComponent extends ListComponent {
    clients = this.records;

    constructor(api, notifier) {
        super(api, notifier, "clients");
    }
}
