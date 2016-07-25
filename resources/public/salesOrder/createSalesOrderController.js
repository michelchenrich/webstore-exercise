angular.module('webStore').controller('createSalesOrderController', function ($scope, $http, $state) {
    var emptyForm =  {productId: '', quantity: 0, customerName: ''};
    $scope.form = emptyForm;
    $scope.selectedProduct = '';
    $scope.messages = {form: '',productId: '', quantity: '', customerName: ''};
    $scope.status = {productId: '', quantity: '', customerName: ''};
    $scope.submit = function () {
        $scope.messages.form = 'Creating sales order...';
        $scope.form.productId = $scope.selectedProduct.id;
        $http.post('/sales-order', $scope.form).then(function (response) {
            $scope.messages.form = '';
            if (response.data.success){
                $scope.messages.form = "Sales order created";
                $scope.form = emptyForm;
            }
            else {
                window.alert("Error");
            }
        });
    };
    $http.get('/products').then(function (response) {
        if (response.data.length == 0)
            window.alert("you need to have at least 1 product to create a sales order");
        $scope.products = response.data;
    });
});