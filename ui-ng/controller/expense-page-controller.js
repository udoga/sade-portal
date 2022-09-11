function ExpensePageController($scope, $routeParams, api) {
  $scope.expense = {};

  $scope.load = function() {
    api.readExpense($routeParams.id).then(function(response) { $scope.expense = response.data.data; });
  };

  $scope.load();
}
