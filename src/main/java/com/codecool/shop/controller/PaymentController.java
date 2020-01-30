package com.codecool.shop.controller;

import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.LocationDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.dao.implementation.JDBC.LocationDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.OrderDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.LineItemJDBC;
import com.codecool.shop.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends CartController {
    private OrderDao orderDao = OrderDaoJDBC.getInstance();
    private LocationDao locationDao = LocationDaoJDBC.getInstance();
    private ShoppingCartDao shoppingCartDao = LineItemJDBC.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start to process POST request for url: '/payment'. session id: {}", req.getSession().getId());
        String name = req.getParameter("name");
        String phone = req.getParameter("phone");
        String email = req.getParameter("email");

        String billingAddress = req.getParameter("billingAddress");
        String billingCity = req.getParameter("billingCity");
        String billingCountry = req.getParameter("billingCountry");
        String billingZip = req.getParameter("billingZip");

        String shippingAddress = req.getParameter("shippingAddress");
        String shippingCity = req.getParameter("shippingCity");
        String shippingCountry = req.getParameter("shippingCountry");
        String shippingZip = req.getParameter("shippingZip");

        Location billingLocation = new Location(billingAddress, billingCity, billingCountry, billingZip);
        Location shippingLocation = new Location(shippingAddress, shippingCity, shippingCountry, shippingZip);

        int billingLocationId = locationDao.addNewLocation(billingLocation);
        billingLocation.setId(billingLocationId);

        int shippingLocationId = locationDao.addNewLocation(shippingLocation);
        shippingLocation.setId(shippingLocationId);

        Order order = new Order(name, phone, email, billingLocation, shippingLocation, shoppingCart, OrderStatus.CONFIRMED);
        int orderId = orderDao.add(order);

        setupShoppingCart(req);
        shoppingCart.setOrderId(orderId);
        shoppingCartDao.add(shoppingCart);

        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("shoppingCart", shoppingCart);
        engine.process("product/payment.html", context, resp.getWriter());

        logger.info("Finnished processing POST request for url: '/payment'. session id: {}", req.getSession().getId());

    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start to process GET request for url: '/payment'. session id: {}", req.getSession().getId());
        setupShoppingCart(req);
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        context.setVariable("shoppingCart", shoppingCart);
        engine.process("product/payment.html", context, resp.getWriter());
        logger.info("Finnished processing GET request for url: '/payment'. session id: {}", req.getSession().getId());
    }

}

