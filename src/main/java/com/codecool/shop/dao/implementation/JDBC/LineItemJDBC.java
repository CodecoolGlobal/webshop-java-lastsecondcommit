package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.ProductDao;
import com.codecool.shop.dao.ShoppingCartDao;
import com.codecool.shop.model.LineItem;
import com.codecool.shop.model.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LineItemJDBC extends JDBC implements ShoppingCartDao {
    private static final Logger logger = LoggerFactory.getLogger(LineItemJDBC.class);
    private static LineItemJDBC instance = null;
    ProductDao productDao = ProductDaoJDBC.getInstance();
    private LineItemJDBC() {
    }

    public static LineItemJDBC getInstance() {
        if (instance == null) {
            instance = new LineItemJDBC();
            logger.trace("ShoppingCartJDBC instance created");
        }
        logger.debug("ShoppingCartJDBC instance is returned");
        return instance;
    }

    @Override
    public void add(ShoppingCart shoppingCart) {
        logger.info("Starting to add line items from shopping cart to database. order id: {}",String.valueOf(shoppingCart.getOrderId()));
        String query = "INSERT INTO line_item (order_id, product_id, quantity) VALUES (?,?,?)";
        for (LineItem lineitem : shoppingCart.getLineItems()) {
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, shoppingCart.getOrderId());
                statement.setInt(2, lineitem.getProduct().getId());
                statement.setInt(3, lineitem.getQuantity());
                statement.executeUpdate();
            } catch (SQLException e) {
                logger.error("An error occurred while adding line items from shopping cart to database. order id: {}", String.valueOf(shoppingCart.getOrderId()), e);
            }
        }
        logger.info("Finished adding line items from shopping cart to database. order id: {}",String.valueOf(shoppingCart.getOrderId()));
    }

    @Override
    public ShoppingCart findByOrderId(int orderId) {
        logger.info("Starting to search for shopping cart data by order id. order id: {}", String.valueOf(orderId));
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
            logger.error("An error occurred while search for shopping cart data by order id. order id: {}", String.valueOf(orderId), e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                logger.error("An error occurred while closing result set after search for shopping cart data by order id. order id: {}", String.valueOf(orderId), e);
            }
        }
        logger.info("Finished search for shopping cart data by order id. order id: {}", String.valueOf(orderId));
        return shoppingCart;
    }
}



