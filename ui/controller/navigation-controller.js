function NavigationController($scope, $location) {
  $scope.navigationOpened = true;

  $scope.toggleNavigation = function() {
    $scope.navigationOpened = !$scope.navigationOpened;
  }

  $scope.getNavigationClass = function() {
    return $scope.navigationOpened? "opened" : "closed";
  }

  $scope.getMainClass = function() {
    return $scope.navigationOpened? "shifted" : "wide";
  }

  $scope.getLinkClass = function(path) {
    return ($location.path().substr(0, path.length) === path) ? 'active' : '';
  }
}
