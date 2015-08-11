package main.domain.account;

public class Email {
    public static final Email EMPTY = new Email("");
    private final String value;

    public Email(String value) {
        this.value = value == null ? "" : value.trim();
    }

    public boolean isValid() {
        return hasOnlyOneAtSymbol() && hasAccountPart() && hasHostPart();
    }

    private boolean hasAccountPart() {
        return value.indexOf('@') > 0;
    }

    private boolean hasHostPart() {
        return value.indexOf('@') < value.length() - 1;
    }

    private boolean hasOnlyOneAtSymbol() {
        return value.indexOf('@') == value.lastIndexOf('@');
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
