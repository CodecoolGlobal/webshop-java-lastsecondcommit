package com.codecool.shop.dao.implementation;

import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartDaoMem implements ShoppingCartDao {

    private List<ShoppingCart> data = new ArrayList<>();
    private static ShoppingCartDaoMem instance = null;

    private ShoppingCartDaoMem() {
    }

    public static ShoppingCartDaoMem getInstance() {
        if (instance == null) {
            instance = new ShoppingCartDaoMem();
        }
        return instance;
    }


    @Override
    public void add(ShoppingCart shoppingCart) {
        shoppingCart.setId(data.size() + 1);
        data.add(shoppingCart);

    }

    @Override
    public ShoppingCart find(int id) {
        for (ShoppingCart cart : data) {
            if (cart.getId() == id) {
                return cart;
            }
        }
        return null;
    }

    @Override
    public List<ShoppingCart> getAll() {
        return null;
    }
}
