package com.codecool.shop.model;

import java.util.*;

public class ShoppingCart {
    protected int id;
    private List<Product> products = new ArrayList<>();

    public List<Product> getProducts() {
        return products;
    }

    public void add(Product product) {
        products.add(product);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

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

}
