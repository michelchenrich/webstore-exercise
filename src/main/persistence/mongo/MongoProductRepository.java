package main.persistence.mongo;

import main.domain.Text;
import main.domain.product.Price;
import main.domain.product.Product;
import main.domain.product.ProductRepository;
import main.domain.product.Quantity;

import java.util.Map;

public class MongoProductRepository extends MongoRepository<Product> implements ProductRepository {
    public MongoProductRepository() {
        super("products");
    }

    protected Product makeNew() {
        return new Product();
    }

    protected void putSetters(Map<String, Setter<Product>> setters) {
        setters.put("name", (p, v) -> p.setName(new Text(v.toString())));
        setters.put("description", (p, v) -> p.setDescription(new Text(v.toString())));
        setters.put("price", (p, v) -> p.setPrice(new Price(v.toString())));
        setters.put("unitsInStock", (p, v) -> p.setUnitsInStock(new Quantity(v.toString())));
    }

    protected void putGetters(Map<String, Getter<Product>> getters) {
        getters.put("name", Product::getName);
        getters.put("description", Product::getDescription);
        getters.put("price", Product::getPrice);
        getters.put("unitsInStock", Product::getUnitsInStock);
    }
}
