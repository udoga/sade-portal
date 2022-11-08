class UserFormController extends FormController {
  user = {};

  constructor(router, api, notifier) {
    super(router, api, "user", notifier);
  }

  getRecord = function() {
    return this.user;
  }
}
