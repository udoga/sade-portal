class LeaveListComponent extends ListComponent {
  leaves = [];

  constructor(client, notifier) {
    super(client, "leaves", notifier);
  }
}
