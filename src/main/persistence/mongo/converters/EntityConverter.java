package main.persistence.mongo.converters;

import main.persistence.mongo.Converter;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

public abstract class EntityConverter<T> implements Converter<T, Document> {
    private Map<String, Getter<T>> getters;
    private Map<String, Setter<T>> setters;

    public EntityConverter() {
        getters = new HashMap<>();
        setters = new HashMap<>();
        defineGetters(getters);
        defineSetters(setters);
    }

    protected abstract void defineGetters(Map<String, Getter<T>> getters);
    protected abstract void defineSetters(Map<String, Setter<T>> setters);
    protected abstract T makeNew();

    public Document to(T entity) {
        Document document = new Document();
        putFields(entity, document);
        return document;
    }

    private void putFields(T object, Document document) {
        for (Map.Entry<String, Getter<T>> getter : getters.entrySet())
            putField(object, document, getter);
    }

    private void putField(T object, Document document, Map.Entry<String, Getter<T>> getter) {
        Object value = getter.getValue().get(object);
        document.put(getter.getKey(), value);
    }

    public T fromPersisted(Document document) {
        T object = makeNew();
        getFields(document, object);
        return object;
    }

    private void getFields(Document document, T object) {
        for (Map.Entry<String, Setter<T>> setter : setters.entrySet())
            getField(document, object, setter);
    }

    private void getField(Document document, T object, Map.Entry<String, Setter<T>> setter) {
        String name = setter.getKey();
        if (document.containsKey(name))
            setter.getValue().set(object, document.get(name));
    }
}
