angular.module('webStore', ['ngRoute']).config(function ($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: '/product/list.html', controller: 'productsController'})
        .when('/login', {templateUrl: '/account/login.html', controller: 'loginController'})
        .when('/register', {templateUrl: '/account/register.html', controller: 'registerController'})
        .when('/product/create', {templateUrl: '/product/create.html', controller: 'createProductController'})
        .otherwise({redirectTo: '/'});
});