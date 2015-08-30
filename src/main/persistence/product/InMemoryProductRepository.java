package main.persistence.product;

import main.domain.product.Product;
import main.persistence.InMemoryRepository;

public class InMemoryProductRepository extends InMemoryRepository<Product> implements ProductRepository {
}