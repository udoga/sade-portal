class SessionPageComponent extends PageComponent {
  session = this.record;

  constructor(router, client) {
    super(router, client, "session");
  }
}
