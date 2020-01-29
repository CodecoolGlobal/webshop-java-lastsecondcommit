package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import org.apache.log4j.xml.DOMConfigurator;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends CartController {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationController.class);
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start to process POST request for url: '/confirmation'. session id: {}", req.getSession().getId());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        setupShoppingCart(req);
        context.setVariable("shoppingCart", shoppingCart);
        engine.process("product/confirmation.html", context, resp.getWriter());
        mailUtility.sendMail("mindentnagyonjolprogramozomiki@gmail.com", "succesfull order from ourbestplants", "you succesfully ordered our best plants. be carefull with the cactus \n"+
                "your order id is:" + String.valueOf(shoppingCart.getOrderId()));

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testkey", "testvalue");
        FileWriter file = new FileWriter("/media/rootMX18.3/home/miki/codecool/oop/6th_TW/webshop-java-lastsecondcommit/orders/order.json");
        file.write(jsonObject.toJSONString());
        file.close();
        logger.info("Finnished processing POST request for url: '/confirmation'. session id: {} order id: {}", req.getSession().getId(), String.valueOf(shoppingCart.getOrderId()));
    }
}
