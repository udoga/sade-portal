class HttpApi {
  get = function(url, options) {
     return fetch(url, options);
  }

  post = function(url, options) {
    options.method = 'POST';
    return fetch(url, options);
  }

  put = function(url, options) {
    options.method = 'PUT';
    return fetch(url, options);
  }

  delete = function(url, options) {
    options.method = 'DELETE';
    return fetch(url, options);
  }
}
