class ReceiptPageController extends PageController {
  receipt = {};

  constructor(router, client) {
    super(router, client, "receipt");
  }

  getRecord = function() {
    return this.receipt;
  }
}
