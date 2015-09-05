package main.persistence.migration;

import main.domain.Entity;
import main.domain.Repository;

public class RepositoryMigrator<TEntity extends Entity> implements Repository<TEntity> {
    private Repository<TEntity> old;
    private Repository<TEntity> current;
    private boolean migrated = false;

    public RepositoryMigrator(Repository<TEntity> old, Repository<TEntity> current) {
        this.old = old;
        this.current = current;
    }

    protected void ensureMigration() {
        if (migrated) return;
        for (TEntity entity : old.getAll())
            current.save(entity);
        migrated = true;
    }

    public void save(TEntity entity) {
        ensureMigration();
        current.save(entity);
    }

    public Iterable<TEntity> getAll() {
        ensureMigration();
        return current.getAll();
    }

    public TEntity getById(String id) {
        ensureMigration();
        return current.getById(id);
    }

    public boolean hasWithId(String id) {
        ensureMigration();
        return current.hasWithId(id);
    }

    public void deleteById(String id) {
        ensureMigration();
        current.deleteById(id);
    }
}
