class PageComponent {
    router = null;
    api = null;
    name = null;
    notifier = null;
    record = {};

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
        this.api.delete(this.record.id).then(json => this.handleRemoveResponse(json));
    }

    handleLoadResponse = function (json) {
        json.data ? Object.assign(this.record, json.data) : this.notifyError(json);
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
}
