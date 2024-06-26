class ClientFormComponent extends FormComponent {
    clientRecord = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "client");
    }
}
