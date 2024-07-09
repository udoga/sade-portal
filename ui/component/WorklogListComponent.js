class WorklogListComponent extends ListComponent {
    worklogs = this.records;

    constructor(api, notifier) {
        super(api, notifier, "worklogs");
    }

    load = function () {
        this.api.listWithDetails().then(json => this.handleLoadResponse(json));
    }
}
