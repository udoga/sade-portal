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
        else this.loadRecord(this.router.getUrlParams());
    }

    save = function () {
        if (this.router.getId()) this.update();
        else this.create();
    }

    handleLoadResponse = function (json) {
        json.data ? this.loadRecord(json.data) : this.notifyError(json);
    }

    handleSaveResponse = function (json) {
        json.data ? this.notifySuccess() : this.notifyError(json);
    }

    loadRecord = function (data) {
        Object.assign(this.record, data);
        if (this.loadRelations) this.loadRelations();
    }

    notifySuccess = function () {
        this.router.route("#/" + this.name + "-list");
        this.notifier.notifyLater(new Notification("success", "Successfully saved."));
    }

    notifyError = function (json) {
        this.notifier.notify(new Notification("error", json.error.message));
    }

    create = function () {
        this.api.create(this.record).then((json) => this.handleSaveResponse(json));
    }

    update = function () {
        this.api.update(this.router.getId(), this.record).then((json) => this.handleSaveResponse(json));
    }
}
