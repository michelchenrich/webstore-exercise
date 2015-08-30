package main.domain.product.reading;

import main.domain.product.creating.CreateProductRequest;
import main.domain.product.creating.CreateProductResponse;
import main.domain.product.creating.CreateProductUseCase;
import main.persistence.product.InMemoryProductRepository;
import main.persistence.product.ProductRepository;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class ReadProductsSummaryUseCaseTest {
    private ProductRepository repository;
    private ArrayList<ProductSummary> response;

    private void whenReadingSummaries() {
        new ReadProductsSummaryUseCase(repository, response).execute();
    }

    private void thenTheSizeMustBe(int size) {
        assertEquals(size, response.size());
    }

    @Before
    public void setUp() throws Exception {
        repository = new InMemoryProductRepository();
        response = new ArrayList<>();
    }

    @Test
    public void givenNoProducts_itReturnsAnEmptyCollection() {
        whenReadingSummaries();
        thenTheSizeMustBe(0);
    }

    @Test
    public void givenAProduct_itMustBeReturnedInTheSummary() {
        givenProduct("name 1", "description 1", 10.0, 1);
        givenProduct("name 2", "description 2", 20.0, 2);
        whenReadingSummaries();
        thenTheSizeMustBe(2);
        andItMustPresentAtIndex(0, "name 1", "description 1", 10.0, 1);
        andItMustPresentAtIndex(1, "name 2", "description 2", 20.0, 2);
    }

    private void andItMustPresentAtIndex(int index, String name, String description, double price, int unitsInStock) {
        ProductSummary summary = response.get(index);
        assertEquals(name, summary.name);
        assertEquals(description, summary.description);
        assertEquals(price, summary.price, .001);
        assertEquals(unitsInStock, summary.unitsInStock);
    }

    private void givenProduct(String name, String description, double price, int unitsInStock) {
        CreateProductRequest request = new CreateProductRequest();
        request.name = name;
        request.description = description;
        request.price = price;
        request.unitsInStock = unitsInStock;
        CreateProductResponse response = new CreateProductResponse();
        new CreateProductUseCase(repository, request, response).execute();
    }
}