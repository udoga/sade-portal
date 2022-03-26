function NavigationController($scope, $location, $window) {
  $scope.reload = function() {
    $scope.mobile = $scope.isMobile();
    $scope.navigationOpened = !$scope.mobile;
  }

  $scope.toggleNavigation = function() {
    $scope.navigationOpened = !$scope.navigationOpened;
  }

  $scope.getNavigationClass = function() {
    return $scope.navigationOpened? "opened" : "";
  }

  $scope.getMainClass = function() {
    return ($scope.navigationOpened && !$scope.mobile)? "shifted" : "";
  }

  $scope.getLinkClass = function(path) {
    return ($location.path().substr(0, path.length) === path)? "active" : "";
  }

  $scope.isMobile = function() {
    return ($window.innerWidth <= 768);
  }

  $scope.onResize = function(event) {
    if ($scope.mobile == $scope.isMobile()) return;
    $scope.reload();
    $scope.$apply();
  }

  $scope.reload();
  $window.addEventListener('resize', $scope.onResize);
}
