package main.domain.product;

import main.domain.Repository;
import main.domain.RepositoryTest;
import main.domain.Text;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public abstract class ProductRepositoryTest extends RepositoryTest<Product> {
    private static final Text NAME1 = new Text("Name 1");
    private static final Text DESCRIPTION1 = new Text("Description 1");
    private static final Price PRICE1 = new Price("10");
    private static final Quantity UNITS_IN_STOCK1 = new Quantity("10");
    private static final Text NAME2 = new Text("Name 2");
    private static final Text DESCRIPTION2 = new Text("Description 2");
    private static final Price PRICE2 = new Price("20");
    private static final Quantity UNITS_IN_STOCK2 = new Quantity("20");
    private ProductRepository repository;

    protected abstract ProductRepository makeRepository();

    protected Repository<Product> getAbstractRepository() {
        return makeRepository();
    }

    protected Product makeNewEntity() {
        return new Product();
    }

    protected Product makeEntityWithId(String id) {
        Product product = new Product();
        product.setId(id);
        product.setName(NAME1);
        product.setDescription(DESCRIPTION1);
        product.setPrice(PRICE1);
        product.setUnitsInStock(UNITS_IN_STOCK1);
        return product;
    }

    protected void changeEntity(Product product) {
        product.setName(NAME2);
        product.setDescription(DESCRIPTION2);
        product.setPrice(PRICE2);
        product.setUnitsInStock(UNITS_IN_STOCK2);
    }

    protected void assertEntityHasSameValues(Product original, Product saved) {
        assertEquals(original.getId(), saved.getId());
        assertEquals(original.getName(), saved.getName());
        assertEquals(original.getDescription(), saved.getDescription());
        assertEquals(original.getPrice(), saved.getPrice());
        assertEquals(original.getUnitsInStock(), saved.getUnitsInStock());
    }

    protected void assertEntityDoesNotHaveSameValues(Product original, Product saved) {
        assertEquals(original.getId(), saved.getId());
        assertNotEquals(original.getName(), saved.getName());
        assertNotEquals(original.getDescription(), saved.getDescription());
        assertNotEquals(original.getPrice(), saved.getPrice());
        assertNotEquals(original.getUnitsInStock(), saved.getUnitsInStock());
    }

    @Before
    public void setUp() throws Exception {
        super.setUp();
        repository = makeRepository();
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
