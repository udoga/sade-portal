class ReceiptPageController extends PageController {
  receipt = {};

  constructor(router, api) {
    super(router, api, "receipt");
  }

  getRecord = function() {
    return this.receipt;
  }
}
