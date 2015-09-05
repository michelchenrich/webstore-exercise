package main.persistence.inmemory;

import main.domain.Entity;
import main.domain.Repository;
import main.persistence.EntityNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<TEntity extends Entity> implements Repository<TEntity> {
    private int incrementalId;
    private Map<String, TEntity> entities = new HashMap<>();

    public boolean hasWithId(String id) {
        return entities.containsKey(id);
    }

    public void deleteById(String id) {
        entities.remove(id);
    }

    public void save(TEntity entity) {
        ensureId(entity);
        entities.put(entity.getId(), makeCopy(entity));
    }

    public TEntity getById(String id) {
        if (hasWithId(id))
            return makeCopy(entities.get(id));
        else
            throw new EntityNotFoundException();
    }

    public Iterable<TEntity> getAll() {
        return entities.values();
    }

    private void ensureId(TEntity entity) {
        if (!entity.hasId())
            entity.setId(makeNextId());
    }

    private String makeNextId() {
        return String.valueOf(++incrementalId);
    }

    @SuppressWarnings("unchecked")
    private TEntity makeCopy(TEntity entity) {
        return (TEntity) entity.copy();
    }
}
