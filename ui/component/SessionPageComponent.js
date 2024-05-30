class SessionPageComponent extends PageComponent {
  session = this.record;

  constructor(router, client, notifier) {
    super(router, client, notifier, "session");
  }
}
