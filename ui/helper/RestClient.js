class RestClient {
  httpClient = null;
  url = null;

  constructor(httpClient, url) {
    this.httpClient = httpClient;
    this.url = "/api" + url;
  }

  list = function() {
    return this.httpClient.get(this.url, {}).then(response => response.json());
  };

  create = function(data) {
    return this.httpClient.post(this.url, {
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    }).then(response => response.json());
  }

  read = function(id) {
    return this.httpClient.get(this.url + id, {}).then(response => response.json());
  }

  update = function(id, data) {
    return this.httpClient.put(this.url + id, {
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    }).then(response => response.json());
  }

  delete = function(id) {
    return this.httpClient.delete(this.url + id, {}).then(response => response.json());
  }
}
