package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Optional;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Cart works");;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ProductDao productDataStore = ProductDaoMem.getInstance();

        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();

        final ShoppingCart shoppingCart = Optional
                .ofNullable(session.getAttribute("cart"))
                .map(ShoppingCart.class::cast)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart();
                    session.setAttribute("cart", cart);
                    return cart;
                });

        Product product = productDataStore.find(Integer.parseInt(req.getParameterValues("prod-id")[0]));
        shoppingCart.add(product);
        System.out.println(shoppingCart.getCart());
    }
}

