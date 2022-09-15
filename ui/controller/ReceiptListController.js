class ReceiptListController extends ListController {
  receipts = [];

  constructor(api) {
    super(api, "receipts");
  }
}