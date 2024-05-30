class ReceiptPageComponent extends PageComponent {
  receipt = this.record;

  constructor(router, client) {
    super(router, client, "receipt");
  }
}
