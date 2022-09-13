class ReceiptListController {
  api = null;
  receipts = [];

  constructor(api) {
    this.api = api;
    this.load();
  }

  load = function() {
    this.api.list().then((json) => { this.emit(json.data); });
  }

  remove = function(id) {
    this.api.delete(id).then((response) => { this.load(); });
  }

  emit = function(receipts) {
    window.dispatchEvent(new CustomEvent("receipts-event", { detail: receipts }));
  }
}
