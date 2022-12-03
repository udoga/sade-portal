class ListController {
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
    json.data? this.emit(json.data) : this.notifier.notify(json.error);
  }

  remove = function(id) {
    this.client.delete(id).then((response) =>  this.load() );
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent( this.name + "-event", { detail: data }));
  }
}
