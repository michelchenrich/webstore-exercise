package main.domain.product.creating;

import main.domain.product.Product;
import main.persistence.product.ProductRepository;

public class CreateProductUseCase {
    private final ProductRepository repository;
    private final CreateProductRequest request;
    private final CreateProductResponse response;

    public CreateProductUseCase(ProductRepository repository, CreateProductRequest request, CreateProductResponse response) {
        this.repository = repository;
        this.request = request;
        this.response = response;
    }

    public void execute() {
        Product product = new Product();
        product.setName(request.name);
        product.setDescription(request.description);
        product.setPrice(request.price);
        product.setUnitsInStock(request.unitsInStock);
        repository.save(product);
    }
}
