class LeaveListController extends ListController {
  leaves = [];

  constructor(client, notifier) {
    super(client, "leaves", notifier);
  }
}
