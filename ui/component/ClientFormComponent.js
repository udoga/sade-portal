class ClientFormComponent extends FormComponent {
    client = this.record;
    sectors = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "client");
    }

    loadAll = function (sectorApi) {
        this.load();
        this.loadSummarized(sectorApi, this.sectors);
    }
}
