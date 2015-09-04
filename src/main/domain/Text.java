package main.domain;

public class Text {
    public static final Text EMPTY = new Text("");
    protected final String value;

    public Text(String value) {
        this.value = value == null ? "" : value.trim();
    }

    public String toString() {
        return value;
    }

    public boolean equals(Object other) {
        return this.getClass().isInstance(other) && equalsText((Text) other);
    }

    private boolean equalsText(Text other) {
        return value.equals(other.value);
    }

    public boolean isValid() {
        return value.length() > 0;
    }
}
