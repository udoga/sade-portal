class FormController {
  router = null;
  api = null;
  record = {};
  name = null;

  constructor(router, api, name) {
    this.router = router;
    this.api = api;
    this.name = name;
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
    this.api.create(this.record).then((json) => this.quit());
  }

  update = function() {
    this.api.update(this.router.getId(), this.record).then((json) => this.quit());
  }

  quit = function() {
    this.router.route("#/" + this.name + "-list");
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent(this.name + "-event", { detail: data }));
  }
}
