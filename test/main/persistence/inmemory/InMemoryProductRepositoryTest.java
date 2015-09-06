package main.persistence.inmemory;

import main.domain.product.ProductRepository;
import main.domain.product.ProductRepositoryTest;

public class InMemoryProductRepositoryTest extends ProductRepositoryTest {
    protected ProductRepository getRepository() {
        return new InMemoryProductRepository();
    }

    protected String getValidId() {
        return "1";
    }

    protected String getInvalidId() {
        return "";
    }
}