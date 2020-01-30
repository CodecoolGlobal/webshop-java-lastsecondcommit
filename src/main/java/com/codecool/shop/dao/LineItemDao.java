package com.codecool.shop.dao;

import com.codecool.shop.model.ShoppingCart;
import java.util.List;

public interface LineItemDao {

    void add(ShoppingCart shoppingCart);
    ShoppingCart findByOrderId(int OrderId);
}
