package com.codecool.shop.model;

import javax.sound.sampled.Line;
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
        if (lineItems.isEmpty()) {
            addLineItemWithNewProduct(product);
            return;
        }
        for (LineItem lineItem : lineItems) {
            if (lineItem.getProduct().equals(product)) {
                lineItem.changeQuantityBy(1);
                return;
            }
        }
        addLineItemWithNewProduct(product);
    }

    public void removeLineItem(LineItem lineItem) {lineItems.remove(lineItem); }

    public void removeOneProduct(Product product) {
        Iterator<LineItem> iterator = lineItems.iterator();
        while (iterator.hasNext()) {
            LineItem lineItem = iterator.next();
            if (lineItem.getProduct().equals(product)) {
                if (lineItem.getQuantity() == 1) {
                    iterator.remove();
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

    private void addLineItemWithNewProduct(Product product) {
        LineItem newLineItem = new LineItem(product, 1);
        lineItems.add(newLineItem);
    }
}
