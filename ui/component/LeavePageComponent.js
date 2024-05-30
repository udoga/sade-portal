class LeavePageComponent extends PageComponent {
  leave = this.record;

  constructor(router, client, notifier) {
    super(router, client, notifier, "leave");
  }
}
