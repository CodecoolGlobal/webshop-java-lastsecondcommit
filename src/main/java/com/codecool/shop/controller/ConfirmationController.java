package com.codecool.shop.controller;

import com.codecool.shop.config.MyConfig;
import com.codecool.shop.config.TemplateEngineUtil;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.JDBC.OrderDaoJDBC;
import com.codecool.shop.model.OrderStatus;
import com.codecool.shop.model.ShoppingCart;
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
    private OrderDao orderDao = OrderDaoJDBC.getInstance();
    private static final Logger logger = LoggerFactory.getLogger(ConfirmationController.class);
    private static final MyConfig cfg = MyConfig.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        logger.info("Start to process POST request for url: '/confirmation'. session id: {}", req.getSession().getId());
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        setupShoppingCart(req);
        context.setVariable("shoppingCart", shoppingCart);

        int orderId = shoppingCart.getOrderId();
        ChangeOrderStatus(orderId);

        engine.process("product/confirmation.html", context, resp.getWriter());
        mailUtility.sendMail(email,
                "Succesfull order from ourbestplants",
                "You succesfully ordered your best plants. <i> Be careful with the cactus!</i><br>"+
                "Your order ID is (#" + orderId + ")<br><br>" +
                        "Your items: <br>" + shoppingCart.toString());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testkey", "testvalue");
        FileWriter file = new FileWriter(cfg.getProperty("local_storage_path") + "order" + orderId +".json");
        file.write(jsonObject.toJSONString());
        file.close();
        shoppingCart = null;
        logger.info("Finnished processing POST request for url: '/confirmation'. session id: {} order id: {}", req.getSession().getId(), orderId);
    }

    private void ChangeOrderStatus(int orderId) {
        orderDao.changeOrderStatus(orderId, OrderStatus.PAID);
    }

}
