class SessionListComponent extends ListComponent {
  sessions = [];

  constructor(client, notifier) {
    super(client, "sessions", notifier);
  }
}
