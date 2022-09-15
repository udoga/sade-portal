class UserFormController extends FormController {
  user = {};

  constructor(router, api) {
    super(router, api, "user");
  }

  getRecord = function() {
    return this.user;
  }
}
