package main.account;

import java.util.HashMap;
import java.util.Map;

public class InMemoryRepository<TEntity extends Entity> implements Repository<TEntity> {
    private int incrementalId;
    private Map<String, TEntity> users = new HashMap<>();

    public void save(TEntity entity) {
        ensureId(entity);
        users.put(entity.getId(), entity.copy());
    }

    public TEntity getById(String id) {
        if (hasWithId(id))
            return users.get(id);
        else
            throw new EntityNotFoundException();
    }

    public boolean hasWithId(String id) {
        return users.containsKey(id);
    }

    protected Iterable<TEntity> getEntities() {
        return users.values();
    }

    private void ensureId(TEntity entity) {
        if (!entity.hasId())
            entity.setId(makeNextId());
    }

    private String makeNextId() {
        return String.valueOf(++incrementalId);
    }
}
