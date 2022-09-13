class ReceiptFormController {
  router = null;
  api = null;
  receipt = {};

  constructor(router, api) {
    this.router = router;
    this.api = api;
    if (this.router.getId()) this.load(this.router.getId());
  }

  load = function(id) {
    this.api.read(id).then((json) => { this.emit(json.data); });
  }

  save = function() {
    if (this.router.getId()) this.update();
    else this.create();
  }

  create = function() {
    this.api.create(this.receipt).then((json) => this.quit());
  }

  update = function() {
    this.api.update(this.router.getId(), this.receipt).then((json) => this.quit());
  }

  quit = function() {
    this.router.route("#/receipt-list");
  }

  emit = function(receipt) {
    window.dispatchEvent(new CustomEvent("receipt-event", { detail: receipt }));
  }
}
