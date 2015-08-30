angular.module('webStore').controller('productsController', function ($scope, $http) {
    $http.get('/products').then(function (response) {
        if (response.data.length == 0)
            return;
        $scope.products = response.data;
        $scope.hasProducts = true;
    });
});