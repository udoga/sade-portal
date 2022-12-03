class UserPageController extends PageController {
  user = {};

  constructor(router, client) {
    super(router, client, "user");
  }

  getRecord = function() {
    return this.user;
  }
}
