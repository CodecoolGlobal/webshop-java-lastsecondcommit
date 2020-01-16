package com.codecool.shop.dao;

import com.codecool.shop.model.ShoppingCart;
import java.util.List;

public interface ShoppingCartDao {

        void add(ShoppingCart shoppingCart);
        ShoppingCart findByOrderId(int OrderId);


}
