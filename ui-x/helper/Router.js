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
    else if (location.hash.startsWith("#/user-page")) this.emit("user-page");
    else if (location.hash === "#/receipt-list") this.emit("receipt-list");
    else if (location.hash.startsWith("#/receipt-form")) this.emit("receipt-form");
    else if (location.hash.startsWith("#/receipt-page")) this.emit("receipt-page");
    else if (location.hash === "#/person-list") this.emit("person-list");
    else if (location.hash.startsWith("#/person-form")) this.emit("person-form");
    else if (location.hash.startsWith("#/person-page")) this.emit("person-page");
    else if (location.hash === "#/leave-list") this.emit("leave-list");
    else if (location.hash.startsWith("#/leave-form")) this.emit("leave-form");
    else if (location.hash.startsWith("#/leave-page")) this.emit("leave-page");
    else if (location.hash === "#/expense-list") this.emit("expense-list");
    else if (location.hash.startsWith("#/expense-form")) this.emit("expense-form");
    else if (location.hash.startsWith("#/expense-page")) this.emit("expense-page");
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
