class LeaveFormController extends FormController {
  leave = {};

  constructor(router, api, notifier) {
    super(router, api, "leave", notifier);
  }

  getRecord = function() {
    return Object.assign({}, this.leave, {"status": null});
  }
}
