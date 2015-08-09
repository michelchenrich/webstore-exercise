package main.account;

public class Password {
    private final String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean isValid() {
        return !value.isEmpty();
    }

    public String toString() {
        return value;
    }

    public boolean equals(Object other) {
        return other instanceof Password && equalsPassword((Password) other);
    }

    private boolean equalsPassword(Password other) {
        return value.equals(other.value);
    }
}
