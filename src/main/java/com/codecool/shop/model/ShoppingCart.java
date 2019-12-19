package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

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
}
