package main.persistence.product.inmemory;

import main.persistence.product.AbstractProductRepositoryTest;
import main.persistence.product.ProductRepository;

public class InMemoryProductRepositoryTest extends AbstractProductRepositoryTest {
    protected ProductRepository makeRepository() {
        return new InMemoryProductRepository();
    }

    protected String getExampleId() {
        return "1";
    }
}