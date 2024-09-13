class FormComponent {
    router = null;
    api = null;
    notifier = null;
    record = {};

    constructor(router, api, notifier) {
        this.router = router;
        this.api = api;
        this.notifier = notifier;
    }

    load = function () {
        if (this.isEditing()) this.api.read(this.router.getId()).then(json => this.handleLoadResponse(json));
        else Object.assign(this.record, this.router.getUrlParams());
    }

    save = function () {
        if (this.isEditing()) this.update();
        else this.create();
    }

    isEditing = function() {
        return this.router.getId() !== null;
    }

    loadOptions = function (api, array) {
        api.list("options").then(json => json.data? Object.assign(array, json.data) : this.notifyError(json.error));
    }

    handleLoadResponse = function (json) {
        json.data ? Object.assign(this.record, json.data) : this.notifyError(json);
        if (this.afterLoad) this.afterLoad();
    }

    handleSaveResponse = function (json) {
        json.data ? this.notifySuccess() : this.notifyError(json);
    }

    notifySuccess = function () {
        history.back();
        this.notifier.notifyLater(new Notification("success", "Successfully saved."));
    }

    notifyError = function (json) {
        var e = json.error;
        this.notifier.notify(new Notification("error", e.type + " (" + e.status + "): " + e.message));
    }

    create = function () {
        this.api.create(this.record).then((json) => this.handleSaveResponse(json));
    }

    update = function () {
        this.api.update(this.router.getId(), this.record).then((json) => this.handleSaveResponse(json));
    }
}
