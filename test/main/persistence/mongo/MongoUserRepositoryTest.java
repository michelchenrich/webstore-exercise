package main.persistence.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.domain.account.UserRepository;
import main.domain.account.UserRepositoryTest;
import org.junit.Before;

public class MongoUserRepositoryTest extends UserRepositoryTest {
    protected UserRepository makeRepository() {
        return new MongoUserRepository();
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
        database.getCollection("users").drop();
    }
}
