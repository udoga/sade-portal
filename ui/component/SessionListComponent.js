class SessionListComponent extends ListComponent {
  sessions = this.records;

  constructor(client, notifier) {
    super(client, notifier, "sessions");
  }
}
