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
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
