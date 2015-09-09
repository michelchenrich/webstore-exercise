angular.module('webStore').run(function ($rootScope, $state, loginModal) {
    $rootScope.$on('$stateChangeStart', function (event, toState, toParams) {
        if (toState.data && toState.data.logInRequired) {
            if (typeof $rootScope.loggedIn === 'undefined') {
                event.preventDefault();
                $state.go('loadingSession');
            } else if (!$rootScope.loggedIn) {
                event.preventDefault();
                loginModal()
                    .then(function () {
                        $state.go(toState.name, toParams);
                    })
                    .catch(function () {
                        $state.go('register');
                    });
            }
        }
    });
});