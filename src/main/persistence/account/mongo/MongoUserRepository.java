package main.persistence.account.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.result.UpdateResult;
import main.domain.account.Email;
import main.domain.account.Password;
import main.domain.account.User;
import main.persistence.EntityNotFoundException;
import main.persistence.account.UserRepository;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class MongoUserRepository implements UserRepository {
    private final MongoCollection<Document> users;

    public MongoUserRepository() {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        users = database.getCollection("users");
    }

    public void save(User entity) {
        if (exists(entity))
            update(entity);
        else
            insert(entity);
    }

    private boolean exists(User entity) {
        return entity.hasId() && hasWithId(entity.getId());
    }

    private UpdateResult update(User entity) {
        return users.replaceOne(makeIdQuery(entity.getId()), convertToDocument(entity));
    }

    private void insert(User entity) {
        Document document = convertToDocument(entity);
        users.insertOne(document);
        entity.setId(document.get("_id").toString());
    }

    private Document convertToDocument(User entity) {
        Document document = new Document();
        if (entity.hasId())
            document.put("_id", new ObjectId(entity.getId()));
        document.put("email", entity.getEmail().toString());
        document.put("password", entity.getPassword().toString());
        return document;
    }

    private User convertFromDocument(Document document) {
        User user = new User();
        user.setId(document.get("_id").toString());
        tryToSetValue(document, "email", (v) -> user.setEmail(new Email(v)));
        tryToSetValue(document, "password", (v) -> user.setPassword(new Password(v)));
        return user;
    }

    private void tryToSetValue(Document document, String field, Setter setter) {
        if (document.containsKey(field))
            setter.set(document.get(field).toString());
    }

    private interface Setter {
        void set(String value);
    }

    public User getById(String id) {
        if (!hasWithId(id)) throw new EntityNotFoundException();
        return convertFromDocument(users.find(makeIdQuery(id)).first());
    }

    public boolean hasWithId(String id) {
        try {
            return users.count(makeIdQuery(id)) > 0;
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    public boolean hasWithEmail(Email email) {
        return users.count(makeEmailQuery(email)) > 0;
    }

    public User getByEmail(Email email) {
        if (!hasWithEmail(email)) throw new EntityNotFoundException();
        return convertFromDocument(users.find(makeEmailQuery(email)).first());
    }

    public void deleteById(String id) {
        users.deleteOne(makeIdQuery(id));
    }

    private Bson makeEmailQuery(Email email) {
        Document query = new Document();
        query.put("email", email.toString());
        return query;
    }

    private Bson makeIdQuery(String id) {
        Document query = new Document();
        query.put("_id", new ObjectId(id));
        return query;
    }

    public Iterable<User> getAll() {
        List<User> all = new ArrayList<>();
        for (Document document : users.find())
            all.add(convertFromDocument(document));
        return all;
    }
}
