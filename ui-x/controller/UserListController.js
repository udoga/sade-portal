class UserListController {
  api = null;
  users = [];

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

  emit = function(users) {
    window.dispatchEvent(new CustomEvent("users-event", { detail: users }));
  }
}
