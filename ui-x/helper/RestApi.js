class RestApi {
  httpApi = null;
  url = null;

  constructor(httpApi, url) {
    this.httpApi = httpApi;
    this.url = url;
  }

  list = function() {
    return this.httpApi.get(this.url, {}).then(response => response.json());
  };

  create = function(data) {
    return this.httpApi.post(this.url, {
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    }).then(response => response.json());
  }

  read = function(id) {
    return this.httpApi.get(this.url + id, {}).then(response => response.json());
  }

  update = function(id, data) {
    return this.httpApi.put(this.url + id, {
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    }).then(response => {response.json()});
  }

  delete = function(id) {
    return this.httpApi.delete(this.url + id, {}).then(response => response.json());
  }
}
