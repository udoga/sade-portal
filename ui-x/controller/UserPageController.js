class UserPageController extends PageController {
  user = null;

  constructor(router, api) {
    super(router, api, "user");
    this.user = this.record;
  }
}
