package com.codecool.shop.model;

public class Order {
    int id;
    String name;
    String phone;
    String email;
    Location billingAddress;
    Location shippingAddress;
    ShoppingCart shoppingCart;
    Enum orderStatus;

    public Order(String name,
                 String phone,
                 String email,
                 Location billingAddress,
                 Location shippingAddress,
                 ShoppingCart shoppingCart,
                 Enum orderStatus) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.billingAddress = billingAddress;
        this.shippingAddress = shippingAddress;
        this.orderStatus = orderStatus;
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

    public Enum getOrderStatus() {
        return orderStatus;
    }

    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }
}
