package main.persistence.product;

import main.domain.product.Product;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

public class InMemoryProductRepositoryTest {
    private ProductRepository repository;

    @Before
    public void setUp() {
        repository = new InMemoryProductRepository();
    }

    @Test
    public void givenNoProducts_returnsEmptyCollection() {
        Iterable<Product> products = repository.getAll();
        assertFalse(products.iterator().hasNext());
    }

    @Test
    public void givenTwoProducts_itReturnsTheTwo() {
        repository.save(new Product());
        repository.save(new Product());
        Iterable<Product> products = repository.getAll();
        int counter = 0;
        for (Product ignored : products) counter++;
        assertEquals(2, counter);
    }
}