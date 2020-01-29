package com.codecool.shop.controller;

import com.codecool.shop.config.MyConfig;
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
import javax.servlet.http.HttpSession;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet(urlPatterns = {"/confirmation"})
public class ConfirmationController extends CartController {
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationController.class);
    private static final MyConfig cfg = MyConfig.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start to process POST request for url: '/confirmation'. session id: {}", req.getSession().getId());
        HttpSession httpSession = req.getSession();
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        setupShoppingCart(req);
        email = (String) httpSession.getAttribute("email");
        logger.debug("email was set to in confirmation: {}",email);
        context.setVariable("shoppingCart", shoppingCart);
        context.setVariable("email", email);
        engine.process("product/confirmation.html", context, resp.getWriter());
        mailUtility.sendMail(email,
                "Succesfull order from ourbestplants",
                "You succesfully ordered your best plants. <i> Be careful with the cactus!</i><br>"+
                "Your order ID is (#" + String.valueOf(shoppingCart.getOrderId()) + ")<br><br>" +
                        "Your items: <br>" + shoppingCart.toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testkey", "testvalue");
        FileWriter file = new FileWriter(cfg.getProperty("local_storage_path")+"order"+ String.valueOf(shoppingCart.getOrderId()) +".json");
        file.write(jsonObject.toJSONString());
        file.close();
        logger.info("Finnished processing POST request for url: '/confirmation'. session id: {} order id: {}", req.getSession().getId(), String.valueOf(shoppingCart.getOrderId()));
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start to process GET request for url: '/confirmation'. session id: {}", req.getSession().getId());
        setupShoppingCart(req);
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("email", email);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("shoppingCart", shoppingCart);

        context.setVariable("email", email);
        engine.process("product/confirmation.html", context, resp.getWriter());
        logger.info("Finnished processing GET request for url: '/confirmation'. session id: {}", req.getSession().getId());
    }
}
