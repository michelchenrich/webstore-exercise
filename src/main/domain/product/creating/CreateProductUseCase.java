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
        response.invalidName = request.name == null || request.name.trim().isEmpty();
        response.invalidDescription = request.description == null || request.description.trim().isEmpty();
        response.invalidPrice = request.price <= 0;
        response.invalidUnitsInStock = request.unitsInStock < 0;

        if (response.invalidName || response.invalidPrice || response.invalidDescription || response.invalidUnitsInStock)
            return;

        Product product = new Product();
        product.setName(request.name);
        product.setDescription(request.description);
        product.setPrice(request.price);
        product.setUnitsInStock(request.unitsInStock);
        repository.save(product);
        response.success = true;
    }
}
