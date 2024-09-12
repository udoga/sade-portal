class ProjectFormComponent extends FormComponent {
    project = this.record;
    clients = [];

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadAll = function (clientApi) {
        this.load();
        this.loadOptions(clientApi, this.clients);
    }
}
