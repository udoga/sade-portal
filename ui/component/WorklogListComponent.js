class WorklogListComponent extends ListComponent {
    worklogs = this.records;
    employees = [];
    activities = [];
    projects = [];

    constructor(api, notifier) {
        super(api, notifier, "worklogs");
    }

    load = function () {
        this.api.listWithDetails().then(json => this.handleLoadResponse(json));
    }
}
