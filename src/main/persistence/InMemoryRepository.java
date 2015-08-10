package main.persistence;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<TEntity extends Entity> implements Repository<TEntity> {
    private int incrementalId;
    private Map<String, TEntity> entities = new HashMap<>();

    public void save(TEntity entity) {
        ensureId(entity);
        entities.put(entity.getId(), entity.copy());
    }

    public TEntity getById(String id) {
        if (hasWithId(id))
            return entities.get(id).copy();
        else
            throw new EntityNotFoundException();
    }

    public boolean hasWithId(String id) {
        return entities.containsKey(id);
    }

    public Iterable<TEntity> getEntities() {
        return entities.values();
    }

    private void ensureId(TEntity entity) {
        if (!entity.hasId())
            entity.setId(makeNextId());
    }

    private String makeNextId() {
        return String.valueOf(++incrementalId);
    }
}
