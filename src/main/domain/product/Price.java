package main.domain.product;

public class Price {
    public static final Price ZERO = new Price("0");
    private double value;

    public Price(String value) {
        try {
            this.value = Double.parseDouble(value);
        } catch (NullPointerException | NumberFormatException ignored) {
            this.value = 0.0;
        }
    }

    public double toDouble() {
        return value;
    }

    public boolean isValid() {
        return value > 0;
    }

    public boolean equals(Object other) {
        return other instanceof Price && equalsPrice((Price) other);
    }

    private boolean equalsPrice(Price other) {
        return value == other.value;
    }
}
