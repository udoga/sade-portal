class ReceiptPageController {
  router = null;
  api = null;
  receipt = {};

  constructor(router, api) {
    this.router = router;
    this.api = api;
    this.load(router.getId());
  }

  load = function(id) {
    this.api.read(id).then((json) => { this.emit(json.data); });
  }

  remove = function() {
    this.api.delete(this.receipt.id).then((json) => this.router.route("#/receipt-list"));
  }

  emit = function(receipt) {
    window.dispatchEvent(new CustomEvent("receipt-event", { detail: receipt }));
  }
}
