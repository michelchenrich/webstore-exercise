package main.domain.salesOrder;


import main.domain.Repository;
import main.domain.RepositoryTest;
import main.persistence.mongo.MongoSalesOrderRepository;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public abstract class SalesOrderRepositoryTest extends RepositoryTest<SalesOrder> {

    private SalesOrderRepository repository;

    protected abstract SalesOrderRepository getRepository();

    @Override
    protected Repository<SalesOrder> getAbstractRepository() {
        return getRepository();
    }
    @Before
    public void setUp() throws Exception {
        super.setUp();
        repository = getRepository();
    }


    @Override
    protected String getValidId() {
        return "1";
    }

    @Override
    protected String getInvalidId() {
        return "";
    }

    @Override
    protected SalesOrder makeNewEntity() {
        SalesOrder salesOrder = new SalesOrder();
        return setFakeAttributes(salesOrder);
    }

    private SalesOrder setFakeAttributes(SalesOrder salesOrder) {
        salesOrder.setProductId("3");
        salesOrder.setQuantity(5);
        salesOrder.setCustomerName("John");
        return salesOrder;
    }

    @Override
    protected SalesOrder makeEntityWithId(String id) {
        SalesOrder salesOrder = new SalesOrder(id);
        return setFakeAttributes(salesOrder);
    }

    @Override
    protected void changeEntity(SalesOrder entity) {
        entity.setProductId("2");
        entity.setQuantity(4);
        entity.setCustomerName("Mary");
    }

    @Override
    protected void assertEntityHasSameValues(SalesOrder original, SalesOrder saved) {
        assertEquals(original.getProductId(), saved.getProductId());
        assertEquals(original.getQuantity(), saved.getQuantity());
        assertEquals(original.getCustomerName(), saved.getCustomerName());
    }

    @Override
    protected void assertEntityDoesNotHaveSameValues(SalesOrder original, SalesOrder saved) {
        assertNotEquals(original.getProductId(), saved.getProductId());
        assertNotEquals(original.getQuantity(), saved.getQuantity());
        assertNotEquals(original.getCustomerName(), saved.getCustomerName());
    }
}
