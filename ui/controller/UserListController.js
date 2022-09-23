class UserListController extends ListController {
  users = [];

  constructor(api, notifier) {
    super(api, "users", notifier);
  }
}
