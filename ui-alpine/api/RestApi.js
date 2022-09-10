class RestApi {
  url = null;

  constructor(url) {
    this.url = url;
  }

  get = function(url) {
     return fetch(url).then(response => response.json());
  }

  post = function(url, data) {
    return fetch(url, {
      method: 'POST',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    }).then(response => response.json());
  }

  put = function(url, data) {
    return fetch(url, {
      method: 'PUT',
      headers: {'Content-Type': 'application/json'},
      body: JSON.stringify(data)
    }).then(response => response.json());
  }

  delete_ = function(url) {
    return fetch(url, { method: 'DELETE' }).then(response => response.json());
  }

  list = function() {
    return this.get(this.url);
  };

  create = function(data) {
    return this.post(this.url, data);
  }

  read = function(id) {
    return this.get(this.url + id);
  }

  update = function(id, data) {
    return this.put(this.url + id, data)
  }

  delete = function(id) {
    return this.delete_(this.url + id);
  }
}
