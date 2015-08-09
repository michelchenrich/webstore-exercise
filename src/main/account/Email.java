package main.account;

public class Email {
    private String value;

    public Email(String value) {
        this.value = value;
    }

    public boolean isValid() {
        return !value.isEmpty();
    }

    public String toString() {
        return value;
    }
}
