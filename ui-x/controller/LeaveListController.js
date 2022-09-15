class LeaveListController extends ListController {
  leaves = [];

  constructor(api) {
    super(api, "leaves");
  }
}