package main.persistence.mongo;

import main.domain.Entity;

public interface Getter<TEntity extends Entity> {
    Object get(TEntity entity);
}
