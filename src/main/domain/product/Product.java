package main.domain.product;

import main.domain.Entity;

public class Product extends Entity {
    private String name;
    private String description;
    private double price;
    private int unitsInStock;

    public Product() {
        super("");
    }

    private Product(String id, String name, String description, double price, int unitsInStock) {
        super(id);
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unitsInStock = unitsInStock;
    }

    public Entity copy() {
        return new Product(id, name, description, price, unitsInStock);
    }

    public void setName(String name) {
        this.name = name == null ? "" : name.trim();
    }

    public void setDescription(String description) {
        this.description = description == null ? "" : description.trim();
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setUnitsInStock(int unitsInStock) {
        this.unitsInStock = unitsInStock;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getUnitsInStock() {
        return unitsInStock;
    }

    public boolean isValid() {
        return hasValidName() && hasValidDescription() && hasValidPrice() && hasValidUnitsInStock();
    }

    public boolean hasValidName() {
        return !name.isEmpty();
    }

    public boolean hasValidDescription() {
        return !description.isEmpty();
    }

    public boolean hasValidPrice() {
        return price > 0;
    }

    public boolean hasValidUnitsInStock() {
        return unitsInStock >= 0;
    }
}
