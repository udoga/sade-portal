class ClientListComponent extends ListComponent {
    clientRecords = this.records;

    constructor(client, notifier) {
        super(client, notifier, "clients");
    }
}
