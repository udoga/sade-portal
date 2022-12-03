class UserListController extends ListController {
  users = [];

  constructor(client, notifier) {
    super(client, "users", notifier);
  }
}
