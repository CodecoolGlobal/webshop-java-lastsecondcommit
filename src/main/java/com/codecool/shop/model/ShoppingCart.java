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

    public void add(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    public void remove(LineItem lineItem) {lineItems.remove(lineItem); }

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
