package com.codecool.shop.model;

import java.util.*;

public class ShoppingCart {
    private int orderId;
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        products.add(product);
    }

    public void remove(Product product) {products.remove(product); }

    public int getItemNumberInCart() {
        return products.size();
    }

    public Map<Product, Integer> getProductsStat() {
        Map<Product, Integer> productsStat = new LinkedHashMap<>();
        for (Product product : products) {
            Integer count = productsStat.get(product);
            if (count == null) {
                productsStat.put(product, 1);
            }
            else {
                productsStat.put(product, count + 1);
            }
        }
        return productsStat;
    }

    public float getSumOfPrices() {
        float sumOfPrices = 0;
        for (Product product : products) {
           sumOfPrices += product.getDefaultPrice();
        }
        return sumOfPrices;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
}
