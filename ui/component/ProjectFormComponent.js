class ProjectFormComponent extends FormComponent {
    project = this.record;
    clients = [];

    constructor(router, api, notifier) {
        super(router, api, notifier, "project");
    }

    loadAll = function (clientApi) {
        this.load();
        clientApi.listSummarized()
            .then(json => json.data? Object.assign(this.clients, json.data) : this.notifyError(json));
    }
}
