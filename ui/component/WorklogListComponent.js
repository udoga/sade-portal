class WorklogListComponent extends ListComponent {
    worklogs = this.records;

    constructor(router, api, notifier) {
        super(router, api, notifier);
    }

    loadMore = function () {
        this.query.afterId = this.worklogs.slice(-1)[0].id;
        this.load("detailed");
    }

    hasMore = function () {
        return this.worklogs.length % 100 == 0;
    }
}
