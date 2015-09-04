package main.persistence.product;

public class InMemoryProductRepositoryTest extends AbstractProductRepositoryTest {
    protected ProductRepository makeRepository() {
        return new InMemoryProductRepository();
    }
}