class ReceiptPageComponent extends PageComponent {
    receipt = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "receipt");
    }
}
