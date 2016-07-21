package main.domain.salesOrder;
import static org.junit.Assert.*;

import main.domain.product.Product;
import main.domain.product.ProductRepository;
import main.persistence.inmemory.InMemorySalesOrderRepository;
import main.persistence.inmemory.InMemoryProductRepository;
import org.junit.Before;
import org.junit.Test;

public class CreateSalesOrderUseCaseTest {
    private CreateSalesOrderRequest request;
    private ProductRepository productRepository;
    private CreateSalesOrderResponse response;
    private InMemorySalesOrderRepository salesOrderRepository;

    @Before
    public void setUp(){
        request = new CreateSalesOrderRequest();
        productRepository = new InMemoryProductRepository();
        response = new CreateSalesOrderResponse();
        salesOrderRepository = new InMemorySalesOrderRepository();
    }

    @Test
    public void createASalesOrderWithExistingProductIDAndQuantity() {
        Product product = new Product();
        product.setId("1");
        productRepository.save(product);

        request.productId = "1";
        request.quantity = 3;
        request.customerName = "John";

        new CreateSalesOrderUseCase(salesOrderRepository, productRepository, request, response).execute();

        Iterable<SalesOrder> salesOrders = salesOrderRepository.getAll();
        SalesOrder persistedSalesOrder = salesOrders.iterator().next();

        assertEquals(request.productId, persistedSalesOrder.getProductId());
        assertEquals(request.quantity, persistedSalesOrder.getQuantity());
        assertEquals(request.customerName, persistedSalesOrder.getCustomerName());

        assertTrue(response.success);
    }

    @Test
    public void createASalesOrderWithInvalidProductId(){
        request.productId = "2";
        request.quantity = 3;
        request.customerName = "John";

        new CreateSalesOrderUseCase(salesOrderRepository, productRepository, request, response).execute();

        assertFalse(response.success);
        assertTrue(response.invalidProductId);
    }


}
