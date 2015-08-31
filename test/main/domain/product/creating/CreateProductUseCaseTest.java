package main.domain.product.creating;

import main.domain.product.reading.ProductSummary;
import main.domain.product.reading.ReadProductsSummaryUseCase;
import main.persistence.product.InMemoryProductRepository;
import main.persistence.product.ProductRepository;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CreateProductUseCaseTest {
    private static final String VALID_NAME = "Valid name";
    private static final String VALID_DESCRIPTION = "Valid description";
    private static final String VALID_PRICE = "10.0";
    private static final String VALID_UNITS_IN_STOCK = "0";
    private CreateProductRequest request;
    private CreateProductResponse response;
    private ProductRepository repository;

    private void givenProductInformation(String name, String description, String price, String unitsInStock) {
        request = new CreateProductRequest();
        request.name = name;
        request.description = description;
        request.price = price;
        request.unitsInStock = unitsInStock;
    }

    public void whenCreatingTheProduct() {
        new CreateProductUseCase(repository, request, response).execute();
    }

    private void andItShouldReturnTheErrors(String... expectedErrors) {
        assertFalse(response.success);
        assertArrayEquals(expectedErrors, makeErrorsArray());
    }

    private String[] makeErrorsArray() {
        ArrayList<String> list = new ArrayList<>();
        if (response.invalidName) list.add("invalidName");
        if (response.invalidDescription) list.add("invalidDescription");
        if (response.invalidPrice) list.add("invalidPrice");
        if (response.invalidUnitsInStock) list.add("invalidUnitsInStock");
        return list.toArray(new String[list.size()]);
    }

    private void thenItShouldNotBeCreated() {
        assertFalse(response.success);
        assertEquals(0, getSummaries().size());
    }

    private void thenItShouldBeCreatedWithTheData(String name, String description, String price, String unitsInStock) {
        assertTrue(response.success);
        ProductSummary summary = getSummaries().get(0);
        assertEquals(name, summary.name);
        assertEquals(description, summary.description);
        assertEquals(Double.parseDouble(price), summary.price, .001);
        assertEquals(Integer.parseInt(unitsInStock), summary.unitsInStock);
    }

    private ArrayList<ProductSummary> getSummaries() {
        ArrayList<ProductSummary> summaries = new ArrayList<>();
        new ReadProductsSummaryUseCase(repository, summaries).execute();
        return summaries;
    }

    private void andItShouldNotReturnErrors() {
        assertEquals(0, makeErrorsArray().length);
    }

    @Before
    public void setUp() {
        response = new CreateProductResponse();
        repository = new InMemoryProductRepository();
    }

    @Test
    public void givenNullName_itIsInvalid() {
        givenProductInformation(null, VALID_DESCRIPTION, VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidName");
    }

    @Test
    public void givenEmptyName_itIsInvalid() {
        givenProductInformation("", VALID_DESCRIPTION, VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidName");
    }

    @Test
    public void givenNameWithOnlySpaces_itIsInvalid() {
        givenProductInformation("   ", VALID_DESCRIPTION, VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidName");
    }

    @Test
    public void givenNullDescription_itIsInvalid() {
        givenProductInformation(VALID_NAME, null, VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidDescription");
    }

    @Test
    public void givenEmptyDescription_itIsInvalid() {
        givenProductInformation(VALID_NAME, "", VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidDescription");
    }

    @Test
    public void givenDescriptionWithOnlySpaces_itIsInvalid() {
        givenProductInformation(VALID_NAME, "  ", VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidDescription");
    }

    @Test
    public void givenNullPrice_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, null, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidPrice");
    }

    @Test
    public void givenEmptyPrice_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, "", VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidPrice");
    }

    @Test
    public void givenNonNumericPrice_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, "Not a number", VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidPrice");
    }

    @Test
    public void givenZeroPrice_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, "0.0", VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidPrice");
    }

    @Test
    public void givenNegativePrice_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, "-1.0", VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidPrice");
    }

    @Test
    public void givenNullUnitsInStock_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, VALID_PRICE, null);
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidUnitsInStock");
    }

    @Test
    public void givenEmptyUnitsInStock_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, VALID_PRICE, "");
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidUnitsInStock");
    }

    @Test
    public void givenNonNumericUnitsInStock_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, VALID_PRICE, "Not a number");
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidUnitsInStock");
    }

    @Test
    public void givenNegativeUnitsInStock_itIsInvalid() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, VALID_PRICE, "-1");
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidUnitsInStock");
    }

    @Test
    public void givenCompletelyInvalidData_itAllMustBeInvalid() {
        givenProductInformation(null, "   ", "0.0", "-1");
        whenCreatingTheProduct();
        thenItShouldNotBeCreated();
        andItShouldReturnTheErrors("invalidName", "invalidDescription", "invalidPrice", "invalidUnitsInStock");
    }

    @Test
    public void givenAllValidInput_theProductMustBeCreated() {
        givenProductInformation(VALID_NAME, VALID_DESCRIPTION, VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldBeCreatedWithTheData(VALID_NAME, VALID_DESCRIPTION, VALID_PRICE, VALID_UNITS_IN_STOCK);
        andItShouldNotReturnErrors();
    }

    @Test
    public void givenNameAndDescriptionSurroundedBySpaces_theProductIsCreatedWithTheTextsTrimmed() {
        givenProductInformation("  Valid name  ", "  Valid description  ", VALID_PRICE, VALID_UNITS_IN_STOCK);
        whenCreatingTheProduct();
        thenItShouldBeCreatedWithTheData(VALID_NAME, VALID_DESCRIPTION, VALID_PRICE, VALID_UNITS_IN_STOCK);
        andItShouldNotReturnErrors();
    }
}