package main.persistence.migration;

import main.domain.product.Product;
import main.domain.product.ProductRepository;

public class ProductRepositoryMigrator extends RepositoryMigrator<Product> implements ProductRepository {
    public ProductRepositoryMigrator(ProductRepository old, ProductRepository current) {
        super(old, current);
    }
}
