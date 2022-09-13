class ReceiptPageController extends PageController {
  receipt = null;

  constructor(router, api) {
    super(router, api, "receipt");
    this.receipt = this.record;
  }
}
