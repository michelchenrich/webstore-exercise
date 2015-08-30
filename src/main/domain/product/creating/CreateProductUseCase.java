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
        Product product = buildProduct();
        if (product.isValid())
            save(product);
        else
            sendErrors(product);
    }

    private Product buildProduct() {
        Product product = new Product();
        product.setName(request.name);
        product.setDescription(request.description);
        product.setPrice(request.price);
        product.setUnitsInStock(request.unitsInStock);
        return product;
    }

    private void save(Product product) {
        repository.save(product);
        response.success = true;
    }

    private void sendErrors(Product product) {
        response.invalidName = !product.hasValidName();
        response.invalidDescription = !product.hasValidDescription();
        response.invalidPrice = !product.hasValidPrice();
        response.invalidUnitsInStock = !product.hasValidUnitsInStock();
    }
}
