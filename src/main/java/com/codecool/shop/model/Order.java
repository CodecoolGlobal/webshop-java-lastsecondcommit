package com.codecool.shop.model;

public class Order {
    int id;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public Location getBillingAddress() {
        return billingAddress;
    }

    public Location getShippingAddress() {
        return shippingAddress;
    }
}