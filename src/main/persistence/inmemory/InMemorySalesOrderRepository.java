package main.persistence.inmemory;

import main.domain.salesOrder.SalesOrder;
import main.domain.salesOrder.SalesOrderRepository;
import main.persistence.mongo.MongoSalesOrderRepository;

public class InMemorySalesOrderRepository extends InMemoryRepository<SalesOrder> implements SalesOrderRepository {

}

