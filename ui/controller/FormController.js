class FormController {
  router = null;
  client = null;
  name = null;

  constructor(router, client, name, notifier) {
    this.router = router;
    this.client = client;
    this.name = name;
    this.notifier = notifier;
    if (this.router.getId()) this.load(this.router.getId());
  }

  load = function(id) {
    this.client.read(id).then((json) => { this.handleLoadResponse(json);});
  }

  save = function() {
    if (this.router.getId()) this.update();
    else this.create();
  }

  handleLoadResponse = function(json) {
    json.data? this.emit(json.data) : this.notifyError(json);
  }

  handleSaveResponse = function(json) {
    json.data? this.notifySuccess() : this.notifyError(json);
  }

  notifySuccess = function() {
    this.quit();
    this.notifier.notifyLater(new Notification("success", "Successfully saved."));
  }

  notifyError = function(json) {
    this.notifier.notify(new Notification("error", json.error.message));
  }

  create = function() {
    this.client.create(this.getRecord()).then((json) => this.handleSaveResponse(json));
  }

  update = function() {
    this.client.update(this.router.getId(), this.getRecord()).then((json) => this.handleSaveResponse(json));
  }

  quit = function() {
    this.router.route("#/" + this.name + "-list");
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent(this.name + "-event", { detail: data }));
  }
}
