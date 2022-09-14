class ListController {
  api = null;
  name = null;

  constructor(api, name) {
    this.api = api;
    this.name = name;
    this.load();
  }

  load = function() {
    this.api.list().then((json) => { this.emit(json.data); });
  }

  remove = function(id) {
    this.api.delete(id).then((response) => { this.load(); });
  }

  emit = function(data) {
    window.dispatchEvent(new CustomEvent( this.name + "-event", { detail: data }));
  }
}
