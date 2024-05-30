class UserPageComponent extends PageComponent {
  user = this.record;

  constructor(router, client, notifier) {
    super(router, client, notifier, "user");
  }
}
