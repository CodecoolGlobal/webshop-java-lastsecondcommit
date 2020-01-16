package com.codecool.shop.model;

public class Location {
    int id;
    String address;
    String city;
    String country;
    String zip;

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getZip() {
        return zip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Location(String address, String city, String country, String zip) {
        this.address = address;
        this.city = city;
        this.country = country;
        this.zip = zip;
    }

}