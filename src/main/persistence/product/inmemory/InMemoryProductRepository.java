package main.persistence.product.inmemory;

import main.domain.product.Product;
import main.persistence.InMemoryRepository;
import main.persistence.product.ProductRepository;

public class InMemoryProductRepository extends InMemoryRepository<Product> implements ProductRepository {
}