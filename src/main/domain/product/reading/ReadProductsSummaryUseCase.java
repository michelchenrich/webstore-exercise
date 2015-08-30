package main.domain.product.reading;

import main.domain.product.Product;
import main.persistence.product.ProductRepository;

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
        summary.name = product.getName();
        summary.description = product.getDescription();
        summary.price = product.getPrice();
        summary.unitsInStock = product.getUnitsInStock();
        return summary;
    }
}
