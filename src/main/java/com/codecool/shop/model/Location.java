package com.codecool.shop.model;

public class Location {
    int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    String shippingAddress;
    String shippingCity;
    String shippingCountry;
    String shippingZip;

    public Location(String shippingAddress, String shippingCity, String shippingCountry, String shippingZip) {
        this.shippingAddress = shippingAddress;
        this.shippingCity = shippingCity;
        this.shippingCountry = shippingCountry;
        this.shippingZip = shippingZip;
    }
}
