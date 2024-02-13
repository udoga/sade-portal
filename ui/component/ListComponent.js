class ListComponent {
  client = null;
  name = null;
  notifier = null;
  error = {};

  constructor(client, name, notifier) {
    this.client = client;
    this.name = name;
    this.notifier = notifier;
    this.load();
  }

  load = function() {
    this.client.list().then((json) => this.handle(json));
  }

  handle = function(json) {
    json.data? this.emit(json.data) : this.notifyError(json);
  }

  notifyError = function(json) {
    this.notifier.notify(new Notification("error", json.error.message));
  }

  remove = function(id) {
    this.client.delete(id).then((response) =>  this.load() );
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent( this.name + "-event", { detail: data }));
  }
}
