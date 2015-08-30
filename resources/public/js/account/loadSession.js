angular.module('webStore').factory('loadSession', function ($rootScope, $http, $location) {
    return function () {
        $location.path('/');
        $http.get('/read-user').then(function (response) {
            $rootScope.hasSession = response.data.success;
            $rootScope.noSession = !$rootScope.hasSession;
        });
    };
});