class ReceiptFormComponent extends FormComponent {
  receipt = {};

  constructor(router, client, notifier) {
    super(router, client, "receipt", notifier);
  }

  getRecord = function() {
    return this.receipt;
  }
}
