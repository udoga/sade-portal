class UserFormComponent extends FormComponent {
  user = {};
  retypedPassword = "";

  constructor(router, client, notifier) {
    super(router, client, "user", notifier);
  }

  check = function() {
    if (this.user.password === this.retypedPassword) this.save();
    else notifier.notify(new Notification("error", "Passwords don't match, please try again."));
  }

  getRecord = function() {
    return this.user;
  }
}
