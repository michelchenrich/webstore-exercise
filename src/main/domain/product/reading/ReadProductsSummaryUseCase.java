package main.domain.product.reading;

import main.domain.product.Product;
import main.domain.product.ProductRepository;

import java.util.Collection;

public class ReadProductsSummaryUseCase {
    private final ProductRepository repository;
    private final Collection<ProductSummary> response;

    public ReadProductsSummaryUseCase(ProductRepository repository, Collection<ProductSummary> response) {
        this.repository = repository;
        this.response = response;
    }

    public void execute() {
        for (Product product : repository.getAll())
            response.add(makeProductSummary(product));
    }

    private ProductSummary makeProductSummary(Product product) {
        ProductSummary summary = new ProductSummary();
        summary.id = product.getId();
        summary.name = product.getName().toString();
        summary.description = product.getDescription().toString();
        summary.price = product.getPrice().toDouble();
        summary.unitsInStock = product.getUnitsInStock().toInteger();
        return summary;
    }
}
