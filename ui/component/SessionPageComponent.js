class SessionPageComponent extends PageComponent {
  session = {};

  constructor(router, client) {
    super(router, client, "session");
  }

  getRecord = function() {
    return this.session;
  }
}
