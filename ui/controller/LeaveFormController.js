class LeaveFormController extends FormController {
  leave = {};

  constructor(router, client, notifier) {
    super(router, client, "leave", notifier);
  }

  getRecord = function() {
    return Object.assign({}, this.leave, {"status": null});
  }
}
