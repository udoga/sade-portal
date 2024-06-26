class ClientPageComponent extends PageComponent {
    clientRecord = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "client");
    }
}
