angular.module('webStore').controller('sessionController', function ($scope, $http, loadSession) {
    $scope.logout = function () {
        $http.post('/logout', {}).then(loadSession);
    };
    loadSession();
});