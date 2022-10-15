class FormController {
  router = null;
  api = null;
  name = null;

  constructor(router, api, name, notifier) {
    this.router = router;
    this.api = api;
    this.name = name;
    this.notifier = notifier;
    if (this.router.getId()) this.load(this.router.getId());
  }

  load = function(id) {
    this.api.read(id).then((json) => { this.handle(json);});
  }

  save = function() {
    if (this.router.getId()) this.update();
    else this.create();
  }

  handle = function(json) {
    json.data? this.emit(json.data) : this.notifier.notify(json.error);
  }

  create = function() {
    this.api.create(this.getRecord()).then((json) => this.quit());
  }

  update = function() {
    this.api.update(this.router.getId(), this.getRecord()).then((json) => this.quit());
  }

  quit = function() {
    this.router.route("#/" + this.name + "-list");
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent(this.name + "-event", { detail: data }));
  }
}
