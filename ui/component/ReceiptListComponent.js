class ReceiptListComponent extends ListComponent {
  receipts = [];

  constructor(client, notifier) {
    super(client, "receipts", notifier);
  }
}
