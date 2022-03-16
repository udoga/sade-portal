function ItemListController($scope, $window, api) {
  $scope.items = [];

  $scope.load = function() {
    api.listItems().then(function(response) { $scope.items = response.data.data; });
  }

  $scope.delete = function(id) {
    api.deleteItem(id).then(function(response) { $scope.load(); });
  }

  $scope.load();
};
