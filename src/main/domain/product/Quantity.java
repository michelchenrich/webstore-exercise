package main.domain.product;

public class Quantity {
    public static final Quantity ZERO = new Quantity("0");
    private int value;
    private boolean wasParsed;

    public Quantity(String value) {
        try {
            this.value = Integer.parseInt(value);
            this.wasParsed = true;
        } catch (NullPointerException | NumberFormatException ignored) {
            this.value = 0;
            this.wasParsed = false;
        }
    }

    public boolean isValid() {
        return wasParsed && value >= 0;
    }

    public int toInteger() {
        return value;
    }

    public boolean equals(Object other) {
        return other instanceof Quantity && equalsPhysicalNumber((Quantity) other);
    }

    private boolean equalsPhysicalNumber(Quantity other) {
        return value == other.value;
    }
}
