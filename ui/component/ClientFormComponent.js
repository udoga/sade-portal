class ClientFormComponent extends FormComponent {
    client = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "client");
    }
}
