angular.module('webStore').controller('sessionController', function ($scope, $http, loginModal, $state) {
    $scope.logout = function () {
        $http.post('/logout', {}).then(function () {
            $state.go('loadingSession');
        });
    };
    $scope.login = function () {
        loginModal().then(function () {
            $state.go('webStore')
        });
    };
});