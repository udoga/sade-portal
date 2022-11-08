class ReceiptFormController extends FormController {
  receipt = {};

  constructor(router, api, notifier) {
    super(router, api, "receipt", notifier);
  }

  getRecord = function() {
    return this.receipt;
  }
}
