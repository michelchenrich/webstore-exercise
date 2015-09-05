package main.persistence.mongo;

import main.domain.product.Product;
import main.domain.product.ProductRepository;
import main.persistence.mongo.converters.ProductConverter;

public class MongoProductRepository extends MongoRepository<Product> implements ProductRepository {
    public MongoProductRepository() {
        super("products", new ProductConverter());
    }
}
