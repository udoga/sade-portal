class UserFormController extends FormController {
  user = null;

  constructor(router, api) {
    super(router, api, "user");
    this.user = this.record;
  }
}
