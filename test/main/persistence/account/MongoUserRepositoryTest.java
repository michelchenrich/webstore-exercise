package main.persistence.account;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.persistence.product.AbstractProductRepositoryTest;
import main.persistence.product.ProductRepository;
import org.junit.Before;

public class MongoUserRepositoryTest extends AbstractProductRepositoryTest {
    protected ProductRepository makeRepository() {
        return new MongoProductRepository();
    }

    protected String getExampleId() {
        return "55e8fd90d8699a04b5f41b8e";
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
        database.drop();
    }
}