class ReceiptFormComponent extends FormComponent {
  receipt = this.record;

  constructor(router, client, notifier) {
    super(router, client, "receipt", notifier);
  }
}
