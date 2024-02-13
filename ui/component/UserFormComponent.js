class UserFormComponent extends FormComponent {
  user = {};

  constructor(router, client, notifier) {
    super(router, client, "user", notifier);
  }

  getRecord = function() {
    return this.user;
  }
}
