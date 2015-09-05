package main.persistence.mongo;

import main.domain.Entity;

public interface Setter<TEntity extends Entity> {
    void set(TEntity entity, Object value);
}
