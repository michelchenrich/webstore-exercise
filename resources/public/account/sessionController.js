angular.module('webStore').controller('sessionController', function ($scope, $http, loadSession, loginModal, $state) {
    $scope.logout = function () {
        $http.post('/logout', {}).then(loadSession);
    };
    $scope.login = function () {
        loginModal().then(function () {
            $state.go('webStore')
        });
    };
});