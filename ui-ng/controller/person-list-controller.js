function PersonListController($scope, $window, api) {
  $scope.people = [];

  $scope.load = function() {
    api.listPeople().then(function(response) { $scope.people = response.data.data; });
  }

  $scope.delete = function(id) {
    api.deletePerson(id).then(function(response) { $scope.load(); });
  }

  $scope.load();
};
