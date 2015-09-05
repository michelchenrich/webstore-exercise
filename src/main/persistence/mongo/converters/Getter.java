package main.persistence.mongo.converters;

public interface Getter<T> {
    Object get(T object);
}
