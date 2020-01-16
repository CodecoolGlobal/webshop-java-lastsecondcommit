package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/checkout"})
public class CheckoutController extends CartController {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setupShoppingCart(req);
        int itemNumberInShoppingCart = shoppingCart.getItemNumberInCart();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("itemNumberInShoppingCart", itemNumberInShoppingCart);
        engine.process("product/checkout.html", context, resp.getWriter());

    }

}
