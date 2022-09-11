function LeaveListController($scope, $window, api) {
  $scope.leaves = [];

  $scope.load = function() {
    api.listLeaves().then(function(response) { $scope.leaves = response.data.data; });
  }

  $scope.delete = function(id) {
    api.deleteLeave(id).then(function(response) { $scope.load(); });
  }

  $scope.load();
};
