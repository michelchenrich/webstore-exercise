package main.persistence;

import main.domain.Entity;

public interface Repository<TEntity extends Entity> {
    void save(TEntity entity);
    TEntity getById(String id);
    boolean hasWithId(String id);
    void deleteById(String id);
    Iterable<TEntity> getAll();
}
