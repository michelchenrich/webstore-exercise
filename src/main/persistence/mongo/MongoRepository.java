package main.persistence.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import main.domain.Entity;
import main.domain.Repository;
import main.persistence.EntityNotFoundException;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class MongoRepository<TEntity extends Entity> implements Repository<TEntity> {
    private MongoCollection<Document> collection;
    private Map<String, Setter<TEntity>> setters;
    private Map<String, Getter<TEntity>> getters;

    protected MongoRepository(String collectionName) {
        collection = getCollection(collectionName);
        setters = new HashMap<>();
        getters = new HashMap<>();
        putSetters(setters);
        putGetters(getters);
    }

    protected MongoCollection<Document> getCollection(String name) {
        MongoClientURI uri = new MongoClientURI(System.getenv("MONGOLAB_URI"));
        MongoClient client = new MongoClient(uri);
        MongoDatabase database = client.getDatabase(uri.getDatabase());
        return database.getCollection(name);
    }

    protected abstract void putSetters(Map<String, Setter<TEntity>> setters);
    protected abstract void putGetters(Map<String, Getter<TEntity>> getters);

    public void save(TEntity entity) {
        if (exists(entity))
            update(entity);
        else
            insert(entity);
    }

    private boolean exists(TEntity entity) {
        return entity.hasId() && hasWithId(entity.getId());
    }

    private void update(TEntity entity) {
        Bson query = makeIdQuery(entity.getId());
        Document document = convertToDocument(entity);
        collection.replaceOne(query, document);
    }

    private void insert(TEntity entity) {
        Document document = convertToDocument(entity);
        collection.insertOne(document);
        entity.setId(document.get("_id").toString());
    }

    private Document convertToDocument(TEntity entity) {
        Document document = new Document();
        putId(entity, document);
        putFields(entity, document);
        return document;
    }

    private void putId(TEntity entity, Document document) {
        if (entity.hasId())
            document.put("_id", new ObjectId(entity.getId()));
    }

    private void putFields(TEntity entity, Document document) {
        for (Map.Entry<String, Getter<TEntity>> getter : getters.entrySet())
            putField(entity, document, getter);
    }

    private Object putField(TEntity entity, Document document, Map.Entry<String, Getter<TEntity>> getter) {
        return document.put(getter.getKey(), getter.getValue().get(entity).toString());
    }

    public boolean hasWithId(String id) {
        try {
            return hasWith(makeIdQuery(id));
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    protected boolean hasWith(Bson query) {
        return collection.count(query) > 0;
    }

    public TEntity getById(String id) {
        return getBy(makeIdQuery(id));
    }

    protected TEntity getBy(Bson query) {
        Document document = collection.find(query).first();
        if (document == null) throw new EntityNotFoundException();
        return convertFromDocument(document);
    }

    private Bson makeIdQuery(String id) {
        Document query = new Document();
        query.put("_id", new ObjectId(id));
        return query;
    }

    private TEntity convertFromDocument(Document document) {
        TEntity entity = makeNew();
        entity.setId(document.get("_id").toString());
        getFields(document, entity);
        return entity;
    }

    protected abstract TEntity makeNew();

    private void getFields(Document document, TEntity entity) {
        for (Map.Entry<String, Setter<TEntity>> setter : setters.entrySet())
            getField(document, entity, setter);
    }

    private void getField(Document document, TEntity entity, Map.Entry<String, Setter<TEntity>> setter) {
        String name = setter.getKey();
        if (document.containsKey(name))
            setter.getValue().set(entity, document.get(name));
    }

    public void deleteById(String id) {
        collection.deleteOne(makeIdQuery(id));
    }

    public Iterable<TEntity> getAll() {
        List<TEntity> all = new ArrayList<>();
        for (Document document : collection.find())
            all.add(convertFromDocument(document));
        return all;
    }
}
