class Router {
  notifier = null;
  httpClient = null;

  constructor(notifier, httpClient) {
    this.notifier = notifier;
    this.httpClient = httpClient;
    setTimeout(() => this.handleChange(), 500);
  }

  route = function(hashUrl) {
    location.hash = hashUrl;
  }

  handleChange = function() {
    if (!location.hash) this.route("#/home");
    else this.emitEvent()
  }

  loadView = function(element) {
    var viewPath = "/view/" + this.findView() + ".html";
    this.httpClient.get(viewPath).then(response => response.text()).then(html => { element.innerHTML = html; });
  }

  emitEvent = function() {
    window.dispatchEvent(new CustomEvent("route-event"));
    this.notifier.clear();
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
    if (location.hash === "#/leave-list") return "leave-list";
    if (location.hash.startsWith("#/leave-form")) return "leave-form";
    if (location.hash.startsWith("#/leave-page")) return "leave-page";
    if (location.hash === "#/expense-list") return "expense-list";
    if (location.hash.startsWith("#/expense-form")) return "expense-form";
    if (location.hash.startsWith("#/expense-page")) return "expense-page";
    if (location.hash === "#/receipt-list") return "receipt-list";
    if (location.hash.startsWith("#/receipt-form")) return "receipt-form";
    if (location.hash.startsWith("#/receipt-page")) return "receipt-page";
    return "not-found";
  }
}
