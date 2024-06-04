class UserFormComponent extends FormComponent {
  user = this.record;
  retypedPassword = "";

  constructor(router, client, notifier) {
    super(router, client, notifier, "user");
  }

  check = function() {
    if (this.user.password === this.retypedPassword) this.save();
    else notifier.notify(new Notification("error", "Passwords don't match, please try again."));
  }
}
