class PageComponent {
  router = null;
  client = null;
  name = null;
  record = {};

  constructor(router, client, name) {
    this.router = router;
    this.client = client;
    this.name = name;
  }

  load = function() {
    this.client.read(this.router.getId()).then(json => Object.assign(this.record, json.data));
  }

  remove = function() {
    this.client.delete(this.record.id).then((json) => this.router.route("#/" + this.name + "-list"));
  }
}
