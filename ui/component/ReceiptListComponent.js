class ReceiptListComponent extends ListComponent {
  receipts = this.records;

  constructor(client, notifier) {
    super(client, notifier, "receipts");
  }
}
