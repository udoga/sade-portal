class WorklogPageComponent extends PageComponent {
    worklog = this.record;

    constructor(router, client, notifier) {
      super(router, client, notifier, "worklog");
    }
  }
