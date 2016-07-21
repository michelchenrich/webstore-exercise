package main.domain.salesOrder;

import main.domain.Entity;

public class SalesOrder extends Entity {
    private String productId;
    private int quantity;
    private String customerName;

    public SalesOrder(){
        this("");
    }
    public SalesOrder(String id) {
        super(id);
    }

    @Override
    public Entity copy() {
        SalesOrder copy = new SalesOrder(id);
        copy.setProductId(productId);
        copy.setQuantity(quantity);
        copy.setCustomerName(customerName);

        return copy;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
}
