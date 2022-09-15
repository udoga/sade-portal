class UserListController extends ListController {
  users = [];

  constructor(api) {
    super(api, "users");
  }
}