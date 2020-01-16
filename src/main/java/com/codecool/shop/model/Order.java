package com.codecool.shop.model;

public class Order {
    String name;
    String phone;
    String email;
    Location billingAddress;
    Location shippingAddress;

    public Order(String name, String phone, String email, Location billingAddress, Location shippingAddress) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
    }
}
