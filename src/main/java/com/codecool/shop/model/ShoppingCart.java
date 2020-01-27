package com.codecool.shop.model;

import java.util.*;

public class ShoppingCart {
    private int orderId;
    private List<LineItem> lineItems = new ArrayList<>();

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void addProduct(Product product) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProduct().equals(product)) {
                lineItem.changeQuantityBy(1);
                break;
            } else {
                LineItem newLineItem = new LineItem(product, 1);
                lineItems.add(newLineItem);
            }
        }
    }

    public void removeLineItem(LineItem lineItem) {lineItems.remove(lineItem); }

    public void removeOneProduct(Product product) {
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProduct().equals(product)) {
                if (lineItem.getQuantity() == 1) {
                    removeLineItem(lineItem);
                } else {
                    lineItem.changeQuantityBy(-1);
                }
            }
        }
    }

    public int getItemNumberInCart() {
        int itemNumber = 0;
        for (LineItem lineItem : lineItems) {
            itemNumber += lineItem.getQuantity();
        }
        return itemNumber;
    }

    public float getSumOfPrices() {
        int sum = 0;
        for (LineItem lineItem : lineItems) {
            sum += lineItem.getPrice();
        }
        return sum;
    }

    public boolean isEmpty() {
        return lineItems.isEmpty();
    }
}
