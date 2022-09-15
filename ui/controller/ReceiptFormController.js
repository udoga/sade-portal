class ReceiptFormController extends FormController {
  receipt = {};

  constructor(router, api) {
    super(router, api, "receipt");
  }

  getRecord = function() {
    return this.receipt;
  }
}
