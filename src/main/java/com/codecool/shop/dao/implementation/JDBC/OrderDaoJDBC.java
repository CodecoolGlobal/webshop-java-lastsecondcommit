package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.OrderDao;
import com.codecool.shop.model.Order;
import com.codecool.shop.model.ProductCategory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoJDBC extends JDBC implements OrderDao {

    private static OrderDaoJDBC instance = null;
    SupplierDaoJDBC LocationDaoJDBC = LocationDaoJDBC.getInstance();

    private OrderDaoJDBC() {
    }

    public static OrderDaoJDBC getInstance() {
        if (instance == null) {
            instance = new OrderDaoJDBC();
        }
        return instance;
    }

    @Override
    public void add(Order category) {
        String query = "INSERT INTO user_order " +
                "(name, email, phone_number, billing_address_id, shipping_address_id, order_status) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getEmail());
            statement.setString(3, category.getPhone());
            statement.setInt(3, category.getBillingAddress().getId());
            statement.setInt(3, category.getShippingAddress().getId());
            statement.setString(3, category.getOrderstatus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Order find(int id) {

        String query = "SELECT * FROM user_order WHERE id = ? ;";
        ResultSet resultSet = null;
        Order result = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Order(
                        resultSet.getString("name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number")),
                        resultSet.getString("billing_adress_id")),
                        resultSet.getString("shipping_adress_id")),
                        resultSet.getString("order_status")),
                result.setId(resultSet.getInt("id"));
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
        return result;
    }

    @Override
    public void remove(int id) {

    }

    @Override
    public void removeAll() {

    }

    @Override
    public List<Order> getAll() {
        return null;
    }
}
