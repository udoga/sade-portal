class ListController {
  api = null;
  name = null;
  error = {};

  constructor(api, name) {
    this.api = api;
    this.name = name;
    this.load();
  }

  load = function() {
    this.api.list().then((json) => this.handle(json));
  }

  handle = function(json) {
    json.data? this.emitData(json.data) : this.emitError(json.error);
  }

  remove = function(id) {
    this.api.delete(id).then((response) =>  this.load() );
  }

  emitData = function(data) {
    window.dispatchEvent(new CustomEvent( this.name + "-event", { detail: data }));
  }

  emitError = function(data) {
    window.dispatchEvent(new CustomEvent( "error-event", { detail: data }));
  }
}
