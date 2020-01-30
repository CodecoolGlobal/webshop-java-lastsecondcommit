//package com.codecool.shop.controller;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//public class LoginController extends HttpServlet {
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html;charset=UTF-8");
//        PrintWriter out = resp.getWriter();
//
//        String username = req.getParameter("username");
//        String password = req.getParameter("password");
//
//        if (ValidationController.checkUser(username, password)) {
//            RequestDispatcher rd = req.getRequestDispatcher("Welcome");
//            rd.forward(req, resp);
//        } else {
//            out.println("Incorrect username or password");
//            RequestDispatcher rd = req.getRequestDispatcher("index.html");
//            rd.include(req, resp);
//        }
//    }
//}
