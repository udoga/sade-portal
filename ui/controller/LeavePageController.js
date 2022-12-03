class LeavePageController extends PageController {
  leave = {};

  constructor(router, client) {
    super(router, client, "leave");
  }

  getRecord = function() {
    return this.leave;
  }
}
