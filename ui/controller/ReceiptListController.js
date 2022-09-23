class ReceiptListController extends ListController {
  receipts = [];

  constructor(api, notifier) {
    super(api, "receipts", notifier);
  }
}
