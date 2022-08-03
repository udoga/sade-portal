function Api($http) {
  this.url = "/api";

  this.listExpenses = function() {
    return $http.get(this.url + "/expenses");
  };

  this.createExpense = function(expense) {
    return $http.post(this.url + "/expenses/", expense);
  }

  this.readExpense = function(id) {
    return $http.get(this.url + "/expenses/" + id);
  }

  this.updateExpense = function(id, expense) {
    return $http.put(this.url + "/expenses/" + id, expense);
  }

  this.deleteExpense = function(id) {
    return $http.delete(this.url + "/expenses/" + id);
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

  this.listReceipts = function() {
    return $http.get(this.url + "/receipts");
  };

  this.createReceipt = function(receipt) {
    return $http.post(this.url + "/receipts/", receipt);
  }

  this.readReceipt = function(id) {
    return $http.get(this.url + "/receipts/" + id);
  }

  this.updateReceipt = function(id, receipt) {
    return $http.put(this.url + "/receipts/" + id, receipt);
  }

  this.deleteReceipt = function(id) {
    return $http.delete(this.url + "/receipts/" + id);
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
