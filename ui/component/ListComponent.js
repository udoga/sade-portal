class ListComponent {
  client = null;
  name = null;
  notifier = null;
  records = [];
  error = {};

  constructor(client, name, notifier) {
    this.client = client;
    this.name = name;
    this.notifier = notifier;
  }

  load = function() {
    this.client.list().then(json => this.handle(json));
  }

  handle = function(json) {
    json.data? Object.assign(this.records, json.data) : this.notifyError(json);
  }

  notifyError = function(json) {
    this.notifier.notify(new Notification("error", json.error.message));
  }

  remove = function(id) {
    this.client.delete(id).then((response) =>  this.load() );
  }
}
