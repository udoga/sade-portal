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

    remove = function () {
        if (confirm("Are you sure?"))
            this.api.delete(this.record.id).then(json => this.handleRemoveResponse(json));
    }

    handleLoadResponse = function (json) {
        json.data ? Object.assign(this.record, json.data) : this.notifyError(json);
        if (this.loadRelations) this.loadRelations();
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
        if (this.loadTab) this.loadTab(tab);
    }

    isSelectedTab = function (tab) {
        return this.selectedTab === tab;
    }

    getTabClass = function (tab) {
        return (this.selectedTab === tab)? "selected" : "";
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
}
