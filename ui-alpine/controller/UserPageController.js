class UserPageController {
  router = null;
  api = null;
  user = {};

  constructor(router, api) {
    this.router = router;
    this.api = api;
    this.load(router.getId());
  }

  load = function(id) {
    this.api.read(id).then((json) => { this.emit(json.data); });
  }

  remove = function() {
    this.api.delete(this.user.id).then((json) => this.router.route("#/user-list"));
  }

  emit = function(user) {
    window.dispatchEvent(new CustomEvent("user-event", { detail: user }));
  }
}
