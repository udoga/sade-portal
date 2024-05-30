class UserPageComponent extends PageComponent {
  user = this.record;

  constructor(router, client) {
    super(router, client, "user");
  }
}
