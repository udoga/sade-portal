class ProjectListComponent extends ListComponent {
    projects = this.records;

    constructor(api, notifier) {
        super(api, notifier, "projects");
    }

    load = function () {
        this.api.listDetailed().then(json => this.handleLoadResponse(json));
    }
}
