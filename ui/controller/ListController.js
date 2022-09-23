class ListController {
  api = null;
  name = null;
  notifier = null;
  error = {};

  constructor(api, name, notifier) {
    this.api = api;
    this.name = name;
    this.notifier = notifier;
    this.load();
  }

  load = function() {
    this.api.list().then((json) => this.handle(json));
  }

  handle = function(json) {
    json.data? this.emit(json.data) : this.notifier.notify(json.error);
  }

  remove = function(id) {
    this.api.delete(id).then((response) =>  this.load() );
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent( this.name + "-event", { detail: data }));
  }
}
