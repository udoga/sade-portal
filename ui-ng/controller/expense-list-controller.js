function ExpenseListController($scope, $window, api) {
  $scope.expenses = [];

  $scope.load = function() {
    api.listExpenses().then(function(response) { $scope.expenses = response.data.data; });
  }

  $scope.delete = function(id) {
    api.deleteExpense(id).then(function(response) { $scope.load(); });
  }

  $scope.load();
};
