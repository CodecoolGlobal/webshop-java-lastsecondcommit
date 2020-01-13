package com.codecool.shop.controller;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.ProductDaoMem;
import com.codecool.shop.dao.implementation.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.google.gson.*;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Optional;

@WebServlet(urlPatterns = {"/api/cart"})
public class CartController extends HttpServlet {


    private static final String ID_NAME = "product_id";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("work");

        ProductDao productDataStore = ProductDaoMem.getInstance();
        ShoppingCartDao cartDataStore = ShoppingCartDaoMem.getInstance();

        ShoppingCart shoppingCart = cartDataStore.find(1);

        WebContext context = new WebContext(req, resp, req.getServletContext());
        HttpSession session = req.getSession();

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
        }

        String data = buffer.toString();
        JsonParser jsonParser = new JsonParser();
        JsonElement jsonElement = jsonParser.parse(data);
        String id = "invalid";
        if (jsonElement.isJsonObject()) {
            JsonObject partialProduct = jsonElement.getAsJsonObject();
            if (partialProduct.has(ID_NAME)) {
                id = partialProduct.get(ID_NAME).getAsString();
            } else {
                // TODO client error
            }
        } else {
            // TODO client error
        }
        System.out.println(id);

        System.out.println(data.getClass());

        /*final ShoppingCart shoppingCart = Optional
                .ofNullable(session.getAttribute("cart"))
                .map(ShoppingCart.class::cast)
                .orElseGet(() -> {
                    ShoppingCart cart = new ShoppingCart();
                    session.setAttribute("cart", cart);
                    return cart;
                });*/
        // TODO: try/catch  (client error)
        Product product = productDataStore.find(Integer.parseInt(id));
        shoppingCart.add(product);
        System.out.println("working");
    }
}

