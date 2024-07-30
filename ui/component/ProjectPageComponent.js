class ProjectPageComponent extends PageComponent {
    project = this.record;
    client = {};
    clientApi = null;

    constructor(router, api, notifier, clientApi) {
        super(router, api, notifier, "project");
        this.clientApi = clientApi;
    }

    loadRelations = function () {
        this.selectTab("client");
    }

    loadTab = function (tab) {
        if (tab == "client") this.loadClient();
    }

    loadClient = function () {
        this.clientApi.read(this.project.clientId)
            .then(json => json.data ? Object.assign(this.client, json.data) : this.notifyError(json));
    }
}
