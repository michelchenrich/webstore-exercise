package main.persistence.mongo.converters;

public interface Setter<T> {
    void set(T object, Object value);
}
