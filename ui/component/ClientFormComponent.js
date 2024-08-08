class ClientFormComponent extends FormComponent {
    client = this.record;
    sectors = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "client");
    }

    loadAll = function (sectorApi) {
        this.load();
        sectorApi.listSummarized()
            .then(json => json.data? Object.assign(this.sectors, json.data) : this.notifyError(json.error));
    }
}
