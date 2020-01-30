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
import javax.servlet.http.HttpSession;
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

        setupShoppingCart(req);
        int orderId = shoppingCart.getOrderId();

        clearShoppingCart(req);

        createResponse(req, resp);

        ChangeOrderStatus(orderId);
        sendConfEmail(orderId);
        saveOrderToJSON(orderId); // not implemented correctly

        logger.info("Finnished processing POST request for url: '/confirmation'. session id: {} order id: {}", req.getSession().getId(), orderId);
    }

    private void clearShoppingCart(HttpServletRequest req) {
        HttpSession httpSession = req.getSession();
        httpSession.setAttribute("shoppingCart", new ShoppingCart());
    }

    private void createResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        setupShoppingCart(req);
        context.setVariable("shoppingCart", shoppingCart);
        engine.process("product/confirmation.html", context, resp.getWriter());
    }

    private void saveOrderToJSON(int orderId) throws IOException {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("testkey", "testvalue");
        FileWriter file = new FileWriter(cfg.getProperty("local_storage_path") + "order" + orderId +".json");
        file.write(jsonObject.toJSONString());
        file.close();
    }

    private void sendConfEmail(int orderId) {
        mailUtility.sendMail(getEmailOfOrder(orderId),
                "Succesfull order from ourbestplants",
                "You succesfully ordered your best plants. <i> Be careful with the cactus!</i><br>"+
                "Your order ID is (#" + orderId + ")<br><br>" +
                        "Your items: <br>" + shoppingCart.toString());
    }

    private String getEmailOfOrder(int orderId) {
        return orderDao.find(orderId).getEmail();
    }

    private void ChangeOrderStatus(int orderId) {
        orderDao.changeOrderStatus(orderId, OrderStatus.PAID);
    }

}
