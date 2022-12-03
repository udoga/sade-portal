class PageController {
  router = null;
  client = null;
  name = null;

  constructor(router, client, name) {
    this.router = router;
    this.client = client;
    this.name = name;
    this.load(router.getId());
  }

  load = function(id) {
    this.client.read(id).then((json) => { this.emit(json.data); });
  }

  remove = function() {
    this.client.delete(this.getRecord().id).then((json) => this.router.route("#/" + this.name + "-list"));
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent(this.name + "-event", { detail: data }));
  }
}
