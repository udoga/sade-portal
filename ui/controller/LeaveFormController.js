class LeaveFormController extends FormController {
  leave = {};

  constructor(router, api) {
    super(router, api, "leave");
  }

  getRecord = function() {
    return this.leave;
  }
}
