class ReceiptListComponent extends ListComponent {
    receipts = this.records;

    constructor(api, notifier) {
        super(api, notifier, "receipts");
    }
}
