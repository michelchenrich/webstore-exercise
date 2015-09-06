package main.routes;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoDatabase;
import main.domain.Text;
import main.domain.product.Price;
import main.domain.product.Product;
import main.domain.product.Quantity;
import main.persistence.mongo.MongoProductRepository;
import org.junit.Test;

public class ProductsSummaryRouteTest extends RouteTest {
    @Test
    public void integration() throws Exception {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        database.getCollection("products").drop();
        client.close();

        Product product = new Product();
        product.setId("55ec9e9ad8699a069f77a024");
        product.setName(new Text("Name 1"));
        product.setDescription(new Text("Description 1"));
        product.setPrice(new Price("10.0"));
        product.setUnitsInStock(new Quantity("10"));
        new MongoProductRepository().save(product);

        assertRouteResponse("GET", "/products",
                "[{\"id\":\"55ec9e9ad8699a069f77a024\"," +
                        "\"name\":\"Name 1\"," +
                        "\"description\":\"Description 1\"," +
                        "\"price\":10.0," +
                        "\"unitsInStock\":10}]");
    }
}