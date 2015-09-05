package main.persistence.inmemory;

import main.domain.product.Product;
import main.domain.product.ProductRepository;

public class InMemoryProductRepository extends InMemoryRepository<Product> implements ProductRepository {
}