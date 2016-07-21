package main.domain.salesOrder;

import main.domain.product.ProductRepository;
import main.persistence.mongo.MongoSalesOrderRepository;

public class CreateSalesOrderUseCase {

    private final CreateSalesOrderResponse response;
    private final CreateSalesOrderRequest request;
    private final ProductRepository productRepository;
    private final SalesOrderRepository salesOrderRepository;

    public CreateSalesOrderUseCase(SalesOrderRepository SalesOrderRepository, ProductRepository productRepository, CreateSalesOrderRequest request, CreateSalesOrderResponse response) {
        this.response = response;
        this.request = request;
        this.productRepository = productRepository;
        this.salesOrderRepository = SalesOrderRepository;
    }

    public void execute() {
        if(!productRepository.hasWithId(request.productId))
            response.invalidProductId = true;
        else{
            SalesOrder newSalesOrder = new SalesOrder();
            newSalesOrder.setProductId(request.productId);
            newSalesOrder.setQuantity(request.quantity);
            newSalesOrder.setCustomerName(request.customerName);
            salesOrderRepository.save(newSalesOrder);
            response.success=true;
        }
    }


}

