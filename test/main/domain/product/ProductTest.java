package main.domain.product;

import main.domain.EntityTest;
import static org.junit.Assert.assertEquals;

public class ProductTest extends EntityTest<Product> {
    protected Product makeNewSubject() {
        return new Product();
    }

    protected Product makeSubjectWithData() {
        Product product = makeNewSubject();
        product.setName("name");
        product.setDescription("description");
        product.setPrice(10.0);
        product.setUnitsInStock(10);
        return product;
    }

    protected void assertSameData(Product entity, Product copy) {
        assertEquals(entity.getId(), copy.getId());
        assertEquals(entity.getName(), copy.getName());
        assertEquals(entity.getDescription(), copy.getDescription());
        assertEquals(entity.getPrice(), copy.getPrice(), .001);
        assertEquals(entity.getUnitsInStock(), copy.getUnitsInStock());
    }
}