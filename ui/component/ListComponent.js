class ListComponent {
    client = null;
    notifier = null;
    name = null;
    records = [];
    error = {};

    constructor(client, notifier, name) {
        this.client = client;
        this.notifier = notifier;
        this.name = name;
    }

    load = function () {
        this.client.list().then(json => this.handleLoadResponse(json));
    }

    remove = function (id) {
        this.client.delete(id).then(json => this.handleRemoveResponse(json));
    }

    handleLoadResponse = function (json) {
        json.data ? Object.assign(this.records, json.data) : this.notifyError(json);
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
}
