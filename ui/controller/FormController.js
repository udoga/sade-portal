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
    this.api.read(id).then((json) => { this.handleLoadResponse(json);});
  }

  save = function() {
    if (this.router.getId()) this.update();
    else this.create();
  }

  handleLoadResponse = function(json) {
    json.data? this.emit(json.data) : this.notifier.notify(json.error);
  }

  handleSaveResponse = function(json) {
    json.data? this.quit() : this.notifier.notify(json.error);
  }

  create = function() {
    this.api.create(this.getRecord()).then((json) => this.handleSaveResponse(json));
  }

  update = function() {
    this.api.update(this.router.getId(), this.getRecord()).then((json) => this.handleSaveResponse(json));
  }

  quit = function() {
    this.router.route("#/" + this.name + "-list");
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent(this.name + "-event", { detail: data }));
  }
}
