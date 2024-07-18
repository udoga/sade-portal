class ProjectPageComponent extends PageComponent {
    project = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "project");
    }

    loadRelations = function () {
        this.selectTab("client");
    }
}
