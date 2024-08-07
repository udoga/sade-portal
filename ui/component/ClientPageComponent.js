class ClientPageComponent extends PageComponent {
    client = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "client");
    }

    getSector = function () {
        return this.client.sector ? this.client.sector : {};
    }
}
