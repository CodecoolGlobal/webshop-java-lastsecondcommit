package com.codecool.shop.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/payment"})
public class PaymentController extends CartController{
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


    }

}
