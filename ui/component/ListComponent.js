class ListComponent {
    router = null;
    api = null;
    notifier = null;
    appendedUrl = "";
    query = {};
    defaultQuery = {};
    records = [];
    error = {};

    constructor(router, api, notifier, appendedUrl = "") {
        this.router = router;
        this.api = api;
        this.notifier = notifier;
        this.appendedUrl = appendedUrl;
    }

    load = function () {
        this.query = this.router.getUrlParams();
        if (!this.containsDefaults(this.query, this.defaultQuery)) this.refresh();
        else this.loadMore();
    }

    loadMore = function () {
        this.query.offset = this.records.length;
        this.api.list(this.appendedUrl, this.getFilledQuery()).then(json => this.handleLoadResponse(json));
    }

    refresh = function () {
        this.assignDefaults(this.query, this.defaultQuery);
        this.router.setUrlParams(this.getFilledQuery());
    }

    remove = function (id) {
        if (confirm("Are you sure?"))
            this.api.delete(id).then(json => this.handleRemoveResponse(json));
    }

    loadOptions = function (api, array) {
        api.list("options").then(json => json.data? Object.assign(array, json.data) : this.notifyError(json.error));
    }

    handleLoadResponse = function (json) {
        json.data ? this.records.push(...json.data) : this.notifyError(json);
        if (this.afterLoad) this.afterLoad();
    }

    handleRemoveResponse = function (json) {
        json.data ? this.removeFromTable(json.data.id) : this.notifyError(json);
    }

    removeFromTable = function (id) {
        this.records.splice(this.getIndex(id), 1);
        this.notifier.notify(new Notification("success", "Deleted successfully."));
    }

    notifyError = function (json) {
        var e = json.error;
        this.notifier.notify(new Notification("error", e.type + " (" + e.status + "): " + e.message));
    }

    getIndex = function (id) {
        for (let i = 0; i < this.records.length; i++) if (this.records[i].id === id) return i;
        return null;
    }

    getFilledQuery = function() {
      return Object.fromEntries(Object.entries(this.query).filter(([_, v]) => v != null && v != ""));
    }

    hasMore = function (records) {
        return records.length != 0 && records.length % 100 == 0;
    }

    containsDefaults = function (obj, defaultObj) {
        return Object.keys(defaultObj).every(key => key in obj);
    }

    assignDefaults = function (obj, defaultObj) {
        Object.keys(defaultObj).forEach(key => { if (!(key in obj)) obj[key] = defaultObj[key]; });
    }
}
