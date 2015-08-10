package main.persistence;

class FakeEntity extends Entity {
    private String value;

    public FakeEntity() {
        this("", "");
    }

    public FakeEntity(String id) {
        this(id, "");
    }

    public FakeEntity(String id, String value) {
        super(id);
        this.value = value;
    }

    public FakeEntity copy() {
        return new FakeEntity(id, value);
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean equals(Object other) {
        return other instanceof FakeEntity && equalsFakeEntity((FakeEntity) other);
    }

    private boolean equalsFakeEntity(FakeEntity other) {
        return id.equals(other.id) && value.equals(other.value);
    }
}
