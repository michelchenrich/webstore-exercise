package main.persistence.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.domain.product.ProductRepository;
import main.domain.product.ProductRepositoryTest;
import org.junit.Before;

public class MongoProductRepositoryTest extends ProductRepositoryTest {
    protected ProductRepository makeRepository() {
        return new MongoProductRepository();
    }

    protected String getValidId() {
        return "55e8fd90d8699a04b5f41b8e";
    }

    protected String getInvalidId() {
        return "";
    }

    @Before
    public void setUp() throws Exception {
        setUpDatabase();
        super.setUp();
    }

    private void setUpDatabase() {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        database.getCollection("products").drop();
    }
}