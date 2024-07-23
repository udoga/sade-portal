class WorklogListComponent extends ListComponent {
    worklogs = this.records;

    constructor(api, notifier) {
        super(api, notifier, "worklogs");
    }

    load = function () {
        this.api.listDetailedBy({}).then(json => this.handleLoadResponse(json));
    }

    loadMore = function () {
        var lastWorklog = this.worklogs.slice(-1)[0];
        this.api.listDetailedBy({afterId: lastWorklog.id}).then(json => this.handleLoadResponse(json));
    }
}
