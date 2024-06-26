class ClientListComponent extends ListComponent {
    clients = this.records;

    constructor(client, notifier) {
        super(client, notifier, "clients");
    }
}
