class UserListComponent extends ListComponent {
  users = this.records;

  constructor(client, notifier) {
    super(client, notifier, "users");
  }
}
