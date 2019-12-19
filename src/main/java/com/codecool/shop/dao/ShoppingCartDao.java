package com.codecool.shop.dao;

import com.codecool.shop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartDao {

        void add(ShoppingCart shoppingCart);
        ShoppingCart find(int id);
        List<ShoppingCart> getAll();

}
