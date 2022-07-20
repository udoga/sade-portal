function Api($http) {
  this.url = "/api";

  this.listItems = function() {
    return $http.get(this.url + "/items");
  };

  this.createItem = function(item) {
    return $http.post(this.url + "/items/", item);
  }

  this.readItem = function(id) {
    return $http.get(this.url + "/items/" + id);
  }

  this.updateItem = function(id, item) {
    return $http.put(this.url + "/items/" + id, item);
  }

  this.deleteItem = function(id) {
    return $http.delete(this.url + "/items/" + id);
  }

  this.listPeople = function() {
    return $http.get(this.url + "/people");
  };

  this.createPerson = function(person) {
    return $http.post(this.url + "/people/", person);
  }

  this.readPerson = function(id) {
    return $http.get(this.url + "/people/" + id);
  }

  this.updatePerson = function(id, person) {
    return $http.put(this.url + "/people/" + id, person);
  }

  this.deletePerson = function(id) {
    return $http.delete(this.url + "/people/" + id);
  }

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
