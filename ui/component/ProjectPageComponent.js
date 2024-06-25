class ProjectPageComponent extends PageComponent {
    project = this.record;

    constructor(router, client, notifier) {
        super(router, client, notifier, "project");
    }
  }
