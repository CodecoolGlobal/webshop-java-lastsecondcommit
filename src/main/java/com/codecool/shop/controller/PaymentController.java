package com.codecool.shop.controller;

import com.codecool.shop.dao.LocationDao;
import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.dao.implementation.JDBC.LocationDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.OrderDaoJDBC;
import com.codecool.shop.model.Location;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.orderStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends CartController{
    private OrderDao orderDao = OrderDaoJDBC.getInstance();
    private LocationDao locationDao = LocationDaoJDBC.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

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
        Order order = new Order(name, phone, email, billingLocation, shippingLocation, orderStatus.CONFIRMED);
        orderDao.add(order);

    }

}
