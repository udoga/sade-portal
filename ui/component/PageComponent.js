class PageComponent {
    router = null;
    api = null;
    name = null;
    notifier = null;
    record = {};
    selectedTab = "";

    constructor(router, api, notifier, name) {
        this.router = router;
        this.api = api;
        this.notifier = notifier;
        this.name = name;
    }

    load = function () {
        this.api.read(this.router.getId()).then(json => this.handleLoadResponse(json));
    }

    loadDetailed = function () {
        this.api.read(this.router.getId(), "detailed").then(json => this.handleLoadResponse(json));
    }

    remove = function () {
        if (confirm("Are you sure?"))
            this.api.delete(this.record.id).then(json => this.handleRemoveResponse(json));
    }

    handleLoadResponse = function (json) {
        json.data ? Object.assign(this.record, json.data) : this.notifyError(json);
        if (this.afterLoad) this.afterLoad();
    }

    handleRemoveResponse = function (json) {
        json.data ? this.notifySuccess("Deleted successfully.") : this.notifyError(json);
    }

    notifySuccess = function (message) {
        this.router.route("#/" + this.name + "-list");
        this.notifier.notifyLater(new Notification("success", message));
    }

    notifyError = function (json) {
        this.notifier.notify(new Notification("error", json.error.message));
    }

    selectTab = function (tab) {
        this.selectedTab = tab;
    }

    isSelectedTab = function (tab) {
        return this.selectedTab === tab;
    }

    getTabClass = function (tab) {
        return (this.selectedTab === tab)? "selected" : "";
    }

    loadRelations = function (api, array, appendedUrl = "") {
        var query = { offset: array.length };
        query[this.name + "Id"] = this.router.getId();
        api.list(appendedUrl, query).then(json => json.data? Object.assign(array, json.data) : this.notifyError(json));
    }

    loadRelation = function (api, obj, appendedUrl = "") {
        var query = {};
        query[this.name + "Id"] = this.router.getId();
        api.list(appendedUrl, query).then(json => json.data? Object.assign(obj, json.data[0]) : this.notifyError(json));
    }

    removeRelation = function (api, array, id) {
        if (confirm("Are you sure?"))
            api.delete(id).then(json => json.data ? this.removeFromTable(array, id) : this.notifyError(json));
    }

    removeFromTable = function (records, id) {
        records.splice(this.getIndex(records, id), 1);
        this.notifier.notify(new Notification("success", "Deleted successfully."));
    }

    getIndex = function (records, id) {
        for (let i = 0; i < records.length; i++) if (records[i].id === id) return i;
        return null;
    }

    hasData = function (record) {
        return Object.keys(record).length !== 0;
    }

    hasMore = function (records) {
        return records.length != 0 && records.length % 100 == 0;
    }
}
