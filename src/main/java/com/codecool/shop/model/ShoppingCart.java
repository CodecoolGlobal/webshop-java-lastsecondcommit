package com.codecool.shop.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> cart = new ArrayList<>();

    public ShoppingCart(Product product) {
        cart.add(product);
    }

    public List<Product> getCart() {
        return cart;
    }


}
