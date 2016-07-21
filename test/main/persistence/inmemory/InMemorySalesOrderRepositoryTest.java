package main.persistence.inmemory;

import main.domain.salesOrder.SalesOrderRepository;
import main.domain.salesOrder.SalesOrderRepositoryTest;
import main.persistence.mongo.MongoSalesOrderRepository;

public class InMemorySalesOrderRepositoryTest extends SalesOrderRepositoryTest{
    @Override
    protected SalesOrderRepository getRepository() {
        return new InMemorySalesOrderRepository();
    }
}
