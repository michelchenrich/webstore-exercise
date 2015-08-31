angular.module('webStore').controller('productsController', function ($scope, $http) {
    $http.get('/products').then(function (response) {
        if (response.data.length == 0)
            return;
        $scope.products = response.data;
    });
    $scope.delete = function (index) {
        var product = $scope.products[index];
        $http.delete('/product/' + product.id, {}).then(function () {
            $scope.products.splice(index, 1);
        });
    };
});