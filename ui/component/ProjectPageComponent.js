class ProjectPageComponent extends PageComponent {
    project = this.record;

    constructor(router, api, notifier) {
        super(router, api, notifier, "project");
    }
}
