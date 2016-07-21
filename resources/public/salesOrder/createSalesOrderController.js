angular.module('webStore').controller('createSalesOrderController', function ($scope, $http, $state) {
    $scope.form = {productId: '', quantity: 0, customerName: ''};
    $scope.messages = {form: '',productId: '', quantity: '', customerName: ''};
    $scope.status = {productId: '', quantity: '', customerName: ''};
    $scope.submit = function () {
        $scope.messages.form = 'Creating sales order...';
        $http.post('/sales-order', $scope.form).then(function (response) {
            $scope.messages.form = '';
            if (response.data.success)
                //$state.go('webStore.products');
                window.alert("Sales order created");
            else {
                window.alert("Error");
            }
        });
    };
});