package main.domain;

public abstract class Entity {
    protected String id;

    public Entity(String id) {
        this.id = id;
    }

    public boolean hasId() {
        return !id.isEmpty();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? "" : id.trim();
    }

    public abstract Entity copy();
}
