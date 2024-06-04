class LeaveListComponent extends ListComponent {
  leaves = this.records;

  constructor(client, notifier) {
    super(client, notifier, "leaves");
  }
}
