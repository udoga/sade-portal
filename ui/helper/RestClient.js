class RestClient extends HttpClient {
  router = null;
  url = null;

  constructor(router, url) {
    super();
    this.router = router;
    this.url = "/api" + url;
  }

  list = function() {
    return this.getJson(this.get(this.url, { headers: this.getHeaders() }));
  };

  create = function(data) {
    return this.getJson(this.post(this.url, { headers: this.getHeaders(), body: JSON.stringify(data) }));
  }

  read = function(id) {
    return this.getJson(this.get(this.url + id, { headers: this.getHeaders() }));
  }

  update = function(id, data) {
    return this.getJson(this.put(this.url + id, { headers: this.getHeaders(), body: JSON.stringify(data) }));
  }

  delete = function(id) {
    return this.getJson(this.delete_(this.url + id, { headers: this.getHeaders() }));
  }

  getJson = function(request) {
    return request.then(response => response.json()).then(json => this.checkSession(json));
  }

  getHeaders = function() {
    return {"Content-Type": "application/json", "Authorization": this.router.getToken("Bearer ")};
  }

  checkSession = function(json) {
    if (json.error && json.error.type == "SessionError") this.router.logout();
    return json;
  }
}
