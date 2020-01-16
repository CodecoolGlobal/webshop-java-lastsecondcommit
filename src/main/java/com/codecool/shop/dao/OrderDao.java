package com.codecool.shop.dao;

import com.codecool.shop.model.Order;

import java.util.List;

public interface OrderDao {

    int add(Order order);
    Order find(int id);
    void remove(int id);
    void removeAll();
    List<Order> getAll();


}
