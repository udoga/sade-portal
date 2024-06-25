class ProjectListComponent extends ListComponent {
    projects = this.records;
    clients = [];

    constructor(client, notifier) {
      super(client, notifier, "projects");
    }

    loadAll = function(clientClient) {
      this.load();
      clientClient.list().then(json => json.data? Object.assign(this.clients, json.data) : this.notifyError(json));
    }

    getClientName = function(clientId) {
        for (let c of this.clients) if (c.id === clientId) return (c.name);
        return "-";
      }
  }
