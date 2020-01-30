package com.codecool.shop.controller;

import com.codecool.shop.model.ShoppingCart;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

public abstract class CartController extends HttpServlet {
    ShoppingCart shoppingCart;

    protected void setupShoppingCart(HttpServletRequest request) {
        HttpSession httpSession = request.getSession();
        if (httpSession.getAttribute("shoppingCart") == null) {
            shoppingCart = new ShoppingCart();
            httpSession.setAttribute("shoppingCart", shoppingCart);
        }
        shoppingCart = (ShoppingCart) httpSession.getAttribute("shoppingCart");

    }

}
