class PageController {
  router = null;
  api = null;
  name = null;

  constructor(router, api, name) {
    this.router = router;
    this.api = api;
    this.name = name;
    this.load(router.getId());
  }

  load = function(id) {
    this.api.read(id).then((json) => { this.emit(json.data); });
  }

  remove = function() {
    this.api.delete(this.getRecord().id).then((json) => this.router.route("#/" + this.name + "-list"));
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent(this.name + "-event", { detail: data }));
  }
}
