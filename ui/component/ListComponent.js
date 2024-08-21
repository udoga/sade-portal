class ListComponent {
    router = null;
    api = null;
    notifier = null;
    query = {};
    records = [];
    error = {};

    constructor(router, api, notifier) {
        this.router = router;
        this.api = api;
        this.notifier = notifier;
        this.query = this.router.getUrlParams();
    }

    load = function (appendedUrl = "") {
        this.query.offset = this.records.length;
        this.api.list(appendedUrl, this.getFilledQuery()).then(json => this.handleLoadResponse(json));
    }

    reload = function (appendedUrl = "") {
        this.records.length = 0;
        this.load(appendedUrl);
    }

    remove = function (id) {
        if (confirm("Are you sure?"))
            this.api.delete(id).then(json => this.handleRemoveResponse(json));
    }

    loadSummarized = function (api, array) {
        api.list("summarized").then(json => json.data? Object.assign(array, json.data) : this.notifyError(json.error));
    }

    handleLoadResponse = function (json) {
        json.data ? this.records.push(...json.data) : this.notifyError(json);
    }

    handleRemoveResponse = function (json) {
        json.data ? this.removeFromTable(json.data.id) : this.notifyError(json);
    }

    removeFromTable = function (id) {
        this.records.splice(this.getIndex(id), 1);
        this.notifier.notify(new Notification("success", "Deleted successfully."));
    }

    notifyError = function (json) {
        this.notifier.notify(new Notification("error", json.error.message));
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
}
