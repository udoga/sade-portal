class ClientFormComponent extends FormComponent {
    client = this.record;
    sectors = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (sectorApi) {
        this.load();
        this.loadOptions(sectorApi, this.sectors);
    }
}
