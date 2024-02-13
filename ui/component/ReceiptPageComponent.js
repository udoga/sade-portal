class ReceiptPageComponent extends PageComponent {
  receipt = {};

  constructor(router, client) {
    super(router, client, "receipt");
  }

  getRecord = function() {
    return this.receipt;
  }
}
