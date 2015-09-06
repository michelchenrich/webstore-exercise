package main.persistence.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.domain.product.ProductRepository;
import main.domain.product.ProductRepositoryTest;
import org.bson.types.ObjectId;
import org.junit.Before;

public class MongoProductRepositoryTest extends ProductRepositoryTest {
    private MongoProductRepository repository;

    protected ProductRepository getRepository() {
        return repository;
    }

    protected String getValidId() {
        return new ObjectId().toString();
    }

    protected String getInvalidId() {
        return "some text";
    }

    @Before
    public void setUp() throws Exception {
        dropCollection();
        repository = new MongoProductRepository();
        super.setUp();
    }

    private void dropCollection() {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        database.getCollection("products").drop();
        client.close();
    }
}