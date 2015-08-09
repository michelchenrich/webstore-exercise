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

    public boolean equals(Object other) {
        return other instanceof Email && equalsEmail((Email) other);
    }

    private boolean equalsEmail(Email other) {
        return value.equals(other.value);
    }
}
