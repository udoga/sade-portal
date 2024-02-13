class UserPageComponent extends PageComponent {
  user = {};

  constructor(router, client) {
    super(router, client, "user");
  }

  getRecord = function() {
    return this.user;
  }
}
