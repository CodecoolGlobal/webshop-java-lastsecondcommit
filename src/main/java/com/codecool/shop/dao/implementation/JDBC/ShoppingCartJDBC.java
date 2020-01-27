package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
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
        String query = "INSERT INTO ordered_products (order_id, product_id, quantity) VALUES (?,?,?)";
        for (Map.Entry<Product,Integer> entry : shoppingCart.getProductsStat().entrySet()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, shoppingCart.getOrderId());
                statement.setInt(2, entry.getKey().getId());
                statement.setInt(3, entry.getValue());
                statement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public ShoppingCart findByOrderId(int orderId) {
        String query = "SELECT * FROM ordered_products WHERE order_id = ? ";
        ResultSet resultSet = null;
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setOrderId(orderId);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, orderId);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                shoppingCart.addProduct(productDao.find(resultSet.getInt("product_id")));
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



