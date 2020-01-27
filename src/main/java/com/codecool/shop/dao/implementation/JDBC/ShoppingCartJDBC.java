package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ShoppingCart;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ShoppingCartJDBC extends JDBC implements ShoppingCartDao {

    private static ShoppingCartJDBC instance = null;
    ProductDao productDao = ProductDaoJDBC.getInstance();

    private ShoppingCartJDBC() {
    }

    public static ShoppingCartJDBC getInstance() {
        if (instance == null) {
            instance = new ShoppingCartJDBC();
        }
        return instance;
    }

    @Override
    public void add(ShoppingCart shoppingCart) {
        String query = "INSERT INTO line_item (order_id, product_id, quantity) VALUES (?,?,?)";
        for (LineItem lineitem : shoppingCart.getLineItems()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, shoppingCart.getOrderId());
                statement.setInt(2, lineitem.getProduct().getId());
                statement.setInt(3, lineitem.getQuantity());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ShoppingCart findByOrderId(int orderId) {
        String query = "SELECT * FROM line_item WHERE order_id = ? ";
        ResultSet resultSet = null;
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderId(orderId);

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                LineItem lineItem = new LineItem(
                        productDao.find(resultSet.getInt("product_id")),
                        resultSet.getInt("quantity")
                );
                shoppingCart.addLineItem(lineItem);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return shoppingCart;
    }
}



