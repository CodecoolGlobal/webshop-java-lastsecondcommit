package com.codecool.shop.model;

import java.util.Objects;

public class LineItem {
    private Product product;
    private int quantity;
    private float price;

    public LineItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.price = product.getDefaultPrice() * quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void changeQuantityBy(int i) {
        this.quantity += i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LineItem lineItem = (LineItem) o;
        return Objects.equals(product, lineItem.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product);
    }
}
