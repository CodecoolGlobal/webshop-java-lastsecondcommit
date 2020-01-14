package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.JDBC.JDBC;
import com.codecool.shop.dao.implementation.JDBC.JDBCHelper;
import com.codecool.shop.dao.implementation.Mem.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    private ProductDao testedProductDao = ProductDaoMem.getInstance();
    private ProductCategory testProductCategory = new ProductCategory("Cactus", "Plant", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
    private Supplier testSupplier = new Supplier("Super Flowers Kft.", "Small family business with quality plants");
    private Product testProduct = new Product("Name", 10.1f, "USD", "name", testProductCategory, testSupplier);

    @BeforeEach
    void clearDataBase() {
        Connection connection = JDBCHelper.getConnection();
        String query = "DELETE FROM product";

        try (Statement statement = connection.createStatement())
        {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddIncreasesDataStoreSizeByOne() {
        int size = testedProductDao.getAll().size();
        testedProductDao.add(testProduct);
        assertTrue(testedProductDao.getAll().size() == size + 1);
    }

    @Test
    void testAddProductsMatching() {

    }

    @Test
    void testFindByExistingId() {
    }

    @Test
    void testRemoveByExistingId() {
    }

    @Test
    void testGetBySupplier() {
    }

    @Test
    void testGetByProductCategory() {
    }

    private boolean areProductsMatching(Product product1, Product product2) {
        return product1.getId() == product2.getId()
                && product1.getName().equals(product2.getName())
                && product1.getDescription().equals(product2.getDescription())
                && product1.getDefaultPrice() == product2.getDefaultPrice()
                && product1.getDefaultCurrency().equals(product2.getDefaultCurrency())
                && product1.getSupplier().equals(product2.getSupplier())
                && product1.getProductCategory().equals(product2.getProductCategory());
    }
}