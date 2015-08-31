package main.domain.account;

import main.domain.Text;

public class Email extends Text {
    public static final Email EMPTY = new Email("");

    public Email(String value) {
        super(value);
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
}
