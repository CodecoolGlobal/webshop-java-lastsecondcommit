package com.codecool.shop.dao.implementation.JDBC;

import com.codecool.shop.dao.SupplierDao;
import com.codecool.shop.model.Supplier;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SupplierDaoJDBC extends JDBC implements SupplierDao {
    private static final Logger logger = LoggerFactory.getLogger(SupplierDaoJDBC.class);
    private static SupplierDaoJDBC instance = null;

    private SupplierDaoJDBC() {
    }

    public static SupplierDaoJDBC getInstance() {
        if (instance == null) {
            instance = new SupplierDaoJDBC();
            logger.trace("SupplierDaoJDBC instance created");
        }
        logger.debug("SupplierDaoJDBC instance is returned");
        return instance;
    }

    @Override
    public void add(Supplier supplier) {
        logger.info("Adding new supplier started. supplier name: {}", supplier.getName());
        String query = "INSERT INTO supplier (name, description) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supplier.getName());
            statement.setString(2, supplier.getDescription());
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while SupplierDaoJDBC tried to add supplier to database. supplier name: {}", supplier.getName(), e);
        }
        logger.info("Adding new supplier finished. supplier name: {}", supplier.getName());
    }

    @Override
    public Supplier find(int id) {
        logger.info("Search for supplier started. supplier id: {}", String.valueOf(id));
        String query = "SELECT * FROM supplier WHERE id = ? ;";
        ResultSet resultSet = null;
        Supplier result = null;
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                result.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            logger.error("An error occurred while SupplierDaoJDBC tried to find supplier in database. supplier id: {}", String.valueOf(id), e);
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                logger.error("An error occurred while SupplierDaoJDBC tried to find supplier in database. supplier id: {}", String.valueOf(id), e);
            }
        }
        logger.info("Search for supplier finished. supplier id: {}", String.valueOf(id));
        return result;
    }

    @Override
    public void remove(int id) {
        logger.info("Remove of supplier started. supplier id: {}", String.valueOf(id));
        String query = "DELETE FROM supplier WHERE id = ?;";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            logger.error("An error occurred while SupplierDaoJDBC tried to remove supplier in database. supplier id: {}", String.valueOf(id), e);
        }
        logger.info("Remove of supplier finished. supplier id: {}", String.valueOf(id));
    }

    @Override
    public List<Supplier> getAll() {
        logger.info("Extract all suppliers is started");
        String query = "SELECT * FROM supplier;";
        List<Supplier> resultList = new ArrayList<>();

        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                Supplier supplier = new Supplier(
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                supplier.setId(resultSet.getInt("id"));
                resultList.add(supplier);
            }
        } catch (SQLException e) {
            logger.error("An error occurred while SupplierDaoJDBC tried to list all suppliers in database.", e);
        }
        logger.info("Extract all suppliers is finished");
        return resultList;
    }

    @Override
    public void removeAll() {
        logger.info("Removing all suppliers started");
        String query = "DELETE FROM supplier;" +
                "ALTER SEQUENCE supplier_id_seq RESTART WITH 1;";

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            logger.error("An error occurred while SupplierDaoJDBC tried to remove all suppliers in database.", e);
        }
        logger.info("Removing all suppliers finished");
    }

}
