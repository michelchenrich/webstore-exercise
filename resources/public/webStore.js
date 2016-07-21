angular.module('webStore', ['ui.router', 'ui.bootstrap']).config(function ($stateProvider, $urlRouterProvider, $httpProvider) {
    $stateProvider
        .state('loadingSession', {
            url: '/',
            templateUrl: '/loading.html',
            controller: 'loadSessionController'
        })
        .state('register', {
            url: '/register',
            templateUrl: '/account/register.html',
            controller: 'registerController',
            data: {logInRequired: false}
        })
        .state('webStore', {
            url: '/webStore',
            templateUrl: '/webStore.html',
            data: {logInRequired: true}
        })
        .state('webStore.orders', {
            url: '/orders/',
            templateUrl: '/salesOrder/createSalesOrder.html',
            controller: 'createSalesOrderController'
        })
        .state('webStore.products', {
            url: '/products',
            templateUrl: '/product/list.html',
            controller: 'productsController'
        })
        .state('webStore.createProduct', {
            url: '/products/create',
            templateUrl: '/product/create.html',
            controller: 'createProductController'
        });
    $urlRouterProvider.otherwise('/');
    $httpProvider.interceptors.push(function ($timeout, $q, $injector) {
        var loginModal, $http, $state;

        $timeout(function () {
            loginModal = $injector.get('loginModal');
            $http = $injector.get('$http');
            $state = $injector.get('$state');
        });

        return {
            responseError: function (rejection) {
                if (rejection.status !== 401)
                    return rejection;

                var deferred = $q.defer();

                loginModal()
                    .then(function () {
                        deferred.resolve($http(rejection.config));
                    })
                    .catch(function () {
                        $state.go('register');
                        deferred.reject(rejection);
                    });

                return deferred.promise;
            }
        };
    });
});