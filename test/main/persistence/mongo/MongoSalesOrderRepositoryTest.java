package main.persistence.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.domain.salesOrder.SalesOrderRepository;
import main.domain.salesOrder.SalesOrderRepositoryTest;
import org.bson.types.ObjectId;
import org.junit.Before;

public class MongoSalesOrderRepositoryTest extends SalesOrderRepositoryTest {

    private MongoSalesOrderRepository repository;

    protected SalesOrderRepository getRepository() {
        return repository;
    }

    @Before
    public void setUp() throws Exception {
        dropCollection();
        repository = new MongoSalesOrderRepository();
        super.setUp();
    }

    protected String getValidId() {
        return new ObjectId().toString();
    }

    protected String getInvalidId() {
        return "some text";
    }

    private void dropCollection() {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        database.getCollection("salesOrders").drop();
        client.close();
    }
}

