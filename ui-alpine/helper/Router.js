class Router {
  constructor() {
    setTimeout(() => this.notify(), 100);
  }

  route = function(hashUrl) {
    location.hash = hashUrl;
  }

  notify = function() {
    if (!location.hash) this.route("#/home");
    else if (location.hash === "#/home") this.emit("home");
    else if (location.hash === "#/user-list") this.emit("user-list");
    else if (location.hash.startsWith("#/user-form")) this.emit("user-form");
    else this.emit("not-found");
  }

  emit = function(viewName) {
    window.dispatchEvent(new CustomEvent("route-event", { detail: '/view/' + viewName + '.html' }));
  }

  getId = function() {
    var parts = location.hash.split("/");
    var id = parts[parts.length - 1];
    return isNaN(id)? null : id;
  }
}
