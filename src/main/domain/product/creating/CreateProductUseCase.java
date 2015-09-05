package main.domain.product.creating;

import main.domain.Text;
import main.domain.product.Price;
import main.domain.product.Product;
import main.domain.product.ProductRepository;
import main.domain.product.Quantity;

public class CreateProductUseCase {
    private final ProductRepository repository;
    private final Text name;
    private final Text description;
    private final Price price;
    private final Quantity unitsInStock;
    private final CreateProductResponse response;

    public CreateProductUseCase(ProductRepository repository, CreateProductRequest request, CreateProductResponse response) {
        this.repository = repository;
        name = new Text(request.name);
        description = new Text(request.description);
        price = new Price(request.price);
        unitsInStock = new Quantity(request.unitsInStock);
        this.response = response;
    }

    public void execute() {
        if (isValidRequest())
            create();
        else
            sendErrors();
    }

    private boolean isValidRequest() {
        return name.isValid() && description.isValid() && price.isValid() && unitsInStock.isValid();
    }

    private void create() {
        repository.save(makeProduct());
        response.success = true;
    }

    private Product makeProduct() {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setUnitsInStock(unitsInStock);
        return product;
    }

    private void sendErrors() {
        response.invalidName = !name.isValid();
        response.invalidDescription = !description.isValid();
        response.invalidPrice = !price.isValid();
        response.invalidUnitsInStock = !unitsInStock.isValid();
    }
}
