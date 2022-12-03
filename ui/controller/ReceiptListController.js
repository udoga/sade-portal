class ReceiptListController extends ListController {
  receipts = [];

  constructor(client, notifier) {
    super(client, "receipts", notifier);
  }
}
