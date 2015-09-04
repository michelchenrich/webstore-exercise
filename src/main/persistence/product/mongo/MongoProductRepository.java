package main.persistence.product.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import main.domain.Text;
import main.domain.product.Price;
import main.domain.product.Product;
import main.domain.product.Quantity;
import main.persistence.EntityNotFoundException;
import main.persistence.product.ProductRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoProductRepository implements ProductRepository {
    private final MongoCollection<Document> products;

    public MongoProductRepository() {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        products = database.getCollection("products");
    }

    public void save(Product entity) {
        if (exists(entity))
            update(entity);
        else
            insert(entity);
    }

    private boolean exists(Product entity) {
        return entity.hasId() && hasWithId(entity.getId());
    }

    private UpdateResult update(Product entity) {
        return products.replaceOne(makeIdQuery(entity.getId()), convertToDocument(entity));
    }

    private void insert(Product entity) {
        Document document = convertToDocument(entity);
        products.insertOne(document);
        entity.setId(document.get("_id").toString());
    }

    private Document convertToDocument(Product entity) {
        Document document = new Document();
        if (entity.hasId())
            document.put("_id", new ObjectId(entity.getId()));
        document.put("name", entity.getName().toString());
        document.put("description", entity.getDescription().toString());
        document.put("price", entity.getPrice().toDouble());
        document.put("unitsInStock", entity.getUnitsInStock().toInteger());
        return document;
    }

    private Product convertFromDocument(Document document) {
        Product product = new Product();
        product.setId(document.get("_id").toString());
        tryToSetValue(document, "name", (v) -> product.setName(new Text(v)));
        tryToSetValue(document, "description", (v) -> product.setDescription(new Text(v)));
        tryToSetValue(document, "price", (v) -> product.setPrice(new Price(v)));
        tryToSetValue(document, "unitsInStock", (v) -> product.setUnitsInStock(new Quantity(v)));
        return product;
    }

    private void tryToSetValue(Document document, String field, Setter setter) {
        if (document.containsKey(field))
            setter.set(document.get(field).toString());
    }

    private interface Setter {
        void set(String value);
    }

    public Product getById(String id) {
        if (!hasWithId(id)) throw new EntityNotFoundException();
        return convertFromDocument(products.find(makeIdQuery(id)).first());
    }

    public boolean hasWithId(String id) {
        return products.count(makeIdQuery(id)) > 0;
    }

    public void deleteById(String id) {
        products.deleteOne(makeIdQuery(id));
    }

    private Bson makeIdQuery(String id) {
        Document query = new Document();
        query.put("_id", new ObjectId(id));
        return query;
    }

    public Iterable<Product> getAll() {
        List<Product> all = new ArrayList<>();
        for (Document document : products.find())
            all.add(convertFromDocument(document));
        return all;
    }
}
