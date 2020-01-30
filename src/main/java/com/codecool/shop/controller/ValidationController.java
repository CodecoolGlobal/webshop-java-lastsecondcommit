//package com.codecool.shop.controller;
//
//import com.codecool.shop.dao.ProductDao;
//import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
//
//public class ValidationController {
//
//    private ProductDao productDao = ProductDaoJDBC.getInstance();
//    public static boolean checkUser(String username, String password) {
//
//        boolean loginString = false;
//        try {
//
//            //loading drivers for mysql
//            Class.forName("com.mysql.jdbc.Driver");
//
//            //creating connection with the database
//            Connection con = DriverManager.getConnection("jdbc:mysql:/ /localhost:3306/test","root","studytonight");
//            PreparedStatement ps = con.prepareStatement("select * from register where email=? and pass=?");
//            ps.setString(1, email);
//            ps.setString(2, pass);
//            ResultSet rs =ps.executeQuery();
//            st = rs.next();
//        }
//    }
//}
