class LeaveFormComponent extends FormComponent {
  leave = this.record;

  constructor(router, client, notifier) {
    super(router, client, notifier, "leave");
  }

  saveLeave = function() {
    this.leave.status = null;
    this.save();
  }
}
