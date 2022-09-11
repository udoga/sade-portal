class UserFormController {
  router = null;
  api = null;
  user = {};

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
    this.api.create(this.user).then((json) => this.quit());
  }

  update = function() {
    this.api.update(this.router.getId(), this.user).then((json) => this.quit());
  }

  quit = function() {
    this.router.route("#/user-list");
  }

  emit = function(user) {
    window.dispatchEvent(new CustomEvent("user-event", { detail: user }));
  }
}
