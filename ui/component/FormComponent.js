class FormComponent {
    router = null;
    api = null;
    notifier = null;
    name = null;
    record = {};

    constructor(router, api, notifier, name) {
        this.router = router;
        this.api = api;
        this.notifier = notifier;
        this.name = name;
    }

    load = function () {
        var id = this.router.getId();
        if (id) this.api.read(id).then(json => this.handleLoadResponse(json));
        else Object.assign(this.record, this.router.getUrlParams());
    }

    save = function () {
        if (this.router.getId()) this.update();
        else this.create();
    }

    loadSummarized = function (api, array) {
        api.list("summarized").then(json => json.data? Object.assign(array, json.data) : this.notifyError(json.error));
    }

    handleLoadResponse = function (json) {
        json.data ? Object.assign(this.record, json.data) : this.notifyError(json);
    }

    handleSaveResponse = function (json) {
        json.data ? this.notifySuccess() : this.notifyError(json);
    }

    notifySuccess = function () {
        this.routeOnSuccess();
        this.notifier.notifyLater(new Notification("success", "Successfully saved."));
    }

    notifyError = function (json) {
        var e = json.error;
        this.notifier.notify(new Notification("error", e.type + " (" + e.status + "): " + e.message));
    }

    routeOnSuccess = function () {
        this.router.route("#/" + this.name + "-list");
    }

    create = function () {
        this.api.create(this.record).then((json) => this.handleSaveResponse(json));
    }

    update = function () {
        this.api.update(this.router.getId(), this.record).then((json) => this.handleSaveResponse(json));
    }
}
