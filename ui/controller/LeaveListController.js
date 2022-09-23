class LeaveListController extends ListController {
  leaves = [];

  constructor(api, notifier) {
    super(api, "leaves", notifier);
  }
}
