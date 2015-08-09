package main.account;

public class Password {
    private String value;

    public Password(String value) {
        this.value = value;
    }

    public boolean isValid() {
        return !value.isEmpty();
    }

    public String toString() {
        return value;
    }

    public boolean matches(Password other) {
        return value.equals(other.value);
    }
}
