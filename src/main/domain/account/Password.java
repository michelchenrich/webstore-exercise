package main.domain.account;

public class Password {
    private final String value;

    public Password(String value) {
        this.value = value == null ? "" : value;
    }

    public boolean isValid() {
        return hasMinimumLength() && hasUpperCaseLetter() && hasLowerCaseLetter() && hasNumber();
    }

    private boolean hasMinimumLength() {
        return value.length() > 7;
    }

    private boolean hasLowerCaseLetter() {
        return !value.toUpperCase().equals(value);
    }

    private boolean hasUpperCaseLetter() {
        return !value.toLowerCase().equals(value);
    }

    private boolean hasNumber() {
        for (int number = 0; number < 10; number++)
            if (value.contains(String.valueOf(number)))
                return true;
        return false;
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
