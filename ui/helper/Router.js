class Router {
    notifier = null;
    httpClient = null;
    nestedNodes = null;

    constructor(notifier, httpClient) {
        this.notifier = notifier;
        this.httpClient = httpClient;
        this.nestedNodes = [];
        this.refresh();
    }

    route = function (hashUrl) {
        location.hash = hashUrl;
    }

    refresh = function () {
        var url = location.hash;
        var token = localStorage.getItem("token");
        if (!token && url != "#/login") location.hash = "#/login";
        else if (token && ["", "#", "#/", "#/login"].includes(url)) location.hash = "#/home";
        else this.reloadNodes();
    }

    login = function (token) {
        localStorage.setItem("token", token);
        this.refresh();
    }

    logout = function () {
        localStorage.removeItem("token");
        this.refresh();
    }

    loadNode = function (element) {
        var view = this.findViews()[this.nestedNodes.length];
        this.nestedNodes.push({ "element": element, "view": view });
        this.httpClient.get("/view/" + view + ".html").then(r => r.text()).then(html => { element.innerHTML = html; });
    }

    reloadNodes = function () {
        var i = 0;
        var views = this.findViews();
        while (i < this.nestedNodes.length && this.nestedNodes[i].view == views[i]) i++;
        this.nestedNodes = this.nestedNodes.slice(0, i + 1);
        if (this.nestedNodes.length) this.loadNode(this.nestedNodes.pop().element);
        this.notifier.clear();
    }

    getId = function () {
        var parts = location.hash.split("/");
        var id = parts[parts.length - 1];
        return isNaN(id) ? null : id;
    }

    findViews = function () {
        if (location.hash === "#/login") return ["login"];
        if (location.hash === "#/home") return ["layout", "home"];
        if (location.hash === "#/user-list") return ["layout", "user-list"];
        if (location.hash.startsWith("#/user-form")) return ["layout", "user-form"];
        if (location.hash.startsWith("#/user-page")) return ["layout", "user-page"];
        if (location.hash === "#/session-list") return ["layout", "session-list"];
        if (location.hash.startsWith("#/session-page")) return ["layout", "session-page"];
        if (location.hash === "#/employee-list") return ["layout", "employee-list"];
        if (location.hash.startsWith("#/employee-form")) return ["layout", "employee-form"];
        if (location.hash.startsWith("#/employee-page")) return ["layout", "employee-page"];
        if (location.hash === "#/vacation-list") return ["layout", "vacation-list"];
        if (location.hash.startsWith("#/vacation-form")) return ["layout", "vacation-form"];
        if (location.hash.startsWith("#/vacation-page")) return ["layout", "vacation-page"];
        if (location.hash === "#/expense-list") return ["layout", "expense-list"];
        if (location.hash.startsWith("#/expense-form")) return ["layout", "expense-form"];
        if (location.hash.startsWith("#/expense-page")) return ["layout", "expense-page"];
        if (location.hash === "#/receipt-list") return ["layout", "receipt-list"];
        if (location.hash.startsWith("#/receipt-form")) return ["layout", "receipt-form"];
        if (location.hash.startsWith("#/receipt-page")) return ["layout", "receipt-page"];
        if (location.hash === "#/worklog-list") return ["layout", "worklog-list"];
        if (location.hash.startsWith("#/worklog-form")) return ["layout", "worklog-form"];
        if (location.hash.startsWith("#/worklog-page")) return ["layout", "worklog-page"];
        if (location.hash === "#/project-list") return ["layout", "project-list"];
        if (location.hash.startsWith("#/project-form")) return ["layout", "project-form"];
        if (location.hash.startsWith("#/project-page")) return ["layout", "project-page"];
        return ["layout", "not-found"];
    }
}
