package main.routes;

import main.domain.account.UserRepository;
import main.domain.product.ProductRepository;

public class Dependencies {
    private UserRepository userRepository;
    private ProductRepository productRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }

    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
