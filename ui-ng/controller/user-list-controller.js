function UserListController($scope, $window, api) {
  $scope.users = [];

  $scope.load = function() {
    api.listUsers().then(function(response) { $scope.users = response.data.data; });
  }

  $scope.delete = function(id) {
    api.deleteUser(id).then(function(response) { $scope.load(); });
  }

  $scope.load();
};
