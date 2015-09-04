package main.persistence.account.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.persistence.account.AbstractUserRepositoryTest;
import main.persistence.account.UserRepository;
import org.junit.Before;

public class MongoUserRepositoryTest extends AbstractUserRepositoryTest {
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
        database.drop();
    }
}
