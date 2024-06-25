class ReceiptPageComponent extends PageComponent {
    receipt = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "receipt");
    }
}
