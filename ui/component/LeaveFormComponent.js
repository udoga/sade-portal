class LeaveFormComponent extends FormComponent {
  leave = this.record;

  constructor(router, client, notifier) {
    super(router, client, "leave", notifier);
  }

  saveLeave = function() {
    this.leave.status = null;
    this.save();
  }
}
