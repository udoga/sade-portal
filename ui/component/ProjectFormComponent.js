class ProjectFormComponent extends FormComponent {
    project = this.record;
    clients = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "project");
    }

    loadAll = function (clientApi) {
        this.load();
        this.loadSummarized(clientApi, this.clients);
    }
}
