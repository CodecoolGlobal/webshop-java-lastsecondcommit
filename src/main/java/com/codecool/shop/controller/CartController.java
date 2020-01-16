package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.Mem.ProductDaoMem;
import com.codecool.shop.dao.implementation.Mem.ShoppingCartDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = {"/cart"})
public class CartController extends HttpServlet {
    private  static final int CART_ID = 1;
    private ShoppingCartDao shoppingCartDataStore = ShoppingCartDaoMem.getInstance();
    private ShoppingCart shoppingCart = shoppingCartDataStore.find(CART_ID);


    private static final String ID_NAME = "product_id";

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("shoppingCart", shoppingCart);
        engine.process("product/cart.html", context, resp.getWriter());

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
        // TODO: try/catch  (client error)
        Product product = productDataStore.find(Integer.parseInt(id));
        shoppingCart.add(product);
    }
}




