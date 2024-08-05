class WorklogListComponent extends ListComponent {
    worklogs = this.records;

    constructor(api, notifier) {
        super(api, notifier, "worklogs");
    }

    load = function () {
        this.api.listDetailedBy(this.getFilters()).then(json => this.handleLoadResponse(json));
    }

    getFilters = function () {
        return this.worklogs.length ? { afterId: this.worklogs.slice(-1)[0].id } : {};
    }

    hasMore = function() {
        return this.worklogs.length % 100 == 0;
    }
}
