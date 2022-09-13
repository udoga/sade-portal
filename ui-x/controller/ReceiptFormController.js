class ReceiptFormController extends FormController {
  receipt = null;

  constructor(router, api) {
    super(router, api, "receipt");
    this.receipt = this.record;
  }
}
