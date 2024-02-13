class UserListComponent extends ListComponent {
  users = [];

  constructor(client, notifier) {
    super(client, "users", notifier);
  }
}
