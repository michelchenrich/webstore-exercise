package main.persistence.mongo;

import main.domain.salesOrder.SalesOrder;
import main.domain.salesOrder.SalesOrderRepository;
import main.persistence.mongo.converters.ProductConverter;
import main.persistence.mongo.converters.SalesOrderConverter;
import org.bson.Document;

public class MongoSalesOrderRepository extends MongoRepository<SalesOrder> implements SalesOrderRepository {
    public MongoSalesOrderRepository() {
        super("salesOrders", new SalesOrderConverter());
    }

}
