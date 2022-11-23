class Router {
  notifier = null;

  constructor(notifier) {
    this.notifier = notifier;
    setTimeout(() => this.startRouting(), 500);
  }

  route = function(hashUrl) {
    location.hash = hashUrl;
  }

  startRouting = function() {
    if (!location.hash) this.route("#/home");
    this.emit(this.findView())
    this.notifier.clear();
  }

  emit = function(viewName) {
    window.dispatchEvent(new CustomEvent("route-event", { detail: '/view/' + viewName + '.html' }));
  }

  getId = function() {
    var parts = location.hash.split("/");
    var id = parts[parts.length - 1];
    return isNaN(id)? null : id;
  }

  findView = function() {
    if (location.hash === "#/home") return "home";
    if (location.hash === "#/user-list") return "user-list";
    if (location.hash.startsWith("#/user-form")) return "user-form";
    if (location.hash.startsWith("#/user-page")) return "user-page";
    if (location.hash === "#/receipt-list") return "receipt-list";
    if (location.hash.startsWith("#/receipt-form")) return "receipt-form";
    if (location.hash.startsWith("#/receipt-page")) return "receipt-page";
    if (location.hash === "#/person-list") return "person-list";
    if (location.hash.startsWith("#/person-form")) return "person-form";
    if (location.hash.startsWith("#/person-page")) return "person-page";
    if (location.hash === "#/leave-list") return "leave-list";
    if (location.hash.startsWith("#/leave-form")) return "leave-form";
    if (location.hash.startsWith("#/leave-page")) return "leave-page";
    if (location.hash === "#/expense-list") return "expense-list";
    if (location.hash.startsWith("#/expense-form")) return "expense-form";
    if (location.hash.startsWith("#/expense-page")) return "expense-page";
    return "not-found";
  }
}
