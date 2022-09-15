class UserPageController extends PageController {
  user = {};

  constructor(router, api) {
    super(router, api, "user");
  }

  getRecord = function() {
    return this.user;
  }
}
