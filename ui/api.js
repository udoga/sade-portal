function Api($http) {
  this.url = "http://localhost:7000";

  this.listUsers = function() {
    return $http.get(this.url + "/users");
  };

  this.createUser = function(user) {
    return $http.post(this.url + "/users/", user);
  }

  this.readUser = function(id) {
    return $http.get(this.url + "/users/" + id);
  }

  this.updateUser = function(id, user) {
    return $http.put(this.url + "/users/" + id, user);
  }

  this.deleteUser = function(id) {
    return $http.delete(this.url + "/users/" + id);
  }
}
