class WorklogPageComponent extends PageComponent {
    worklog = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "worklog");
    }

    loadRelations = function () {
        this.selectTab("project");
    }
}
