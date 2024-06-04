class FormComponent {
  router = null;
  client = null;
  notifier = null;
  name = null;
  record = {};

  constructor(router, client, notifier, name) {
    this.router = router;
    this.client = client;
    this.notifier = notifier;
    this.name = name;
  }

  load = function() {
    var id = this.router.getId();
    if (id) this.client.read(id).then(json => this.handleLoadResponse(json));
  }

  save = function() {
    if (this.router.getId()) this.update();
    else this.create();
  }

  handleLoadResponse = function(json) {
    json.data? Object.assign(this.record, json.data) : this.notifyError(json);
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
    this.client.create(this.record).then((json) => this.handleSaveResponse(json));
  }

  update = function() {
    this.client.update(this.router.getId(), this.record).then((json) => this.handleSaveResponse(json));
  }

  quit = function() {
    this.router.route("#/" + this.name + "-list");
  }
}
