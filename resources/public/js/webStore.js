angular.module('webStore', ['ngRoute']).config(function ($routeProvider) {
    $routeProvider
        .when('/', {templateUrl: 'partials/products.html', controller: 'productsController'})
        .when('/login', {templateUrl: '/partials/login.html', controller: 'loginController'})
        .when('/register', {templateUrl: '/partials/register.html', controller: 'registerController'})
        .when('/product/create', {templateUrl: '/partials/create_product.html', controller: 'createProductController'})
        .otherwise({redirectTo: '/'});
});