package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.SupplierDaoJDBC;
import com.codecool.shop.dao.implementation.Mem.ProductDaoMem;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoTest {
    // ProductDaoMem.getInstance() OR ProductDaoJDBC.getInstance()
    private ProductDao testProductDao = ProductDaoJDBC.getInstance();
    private ProductCategoryDao testProductCategoryDao = ProductCategoryDaoJDBC.getInstance();
    private SupplierDao testSupplierDao = SupplierDaoJDBC.getInstance();

    private ProductCategory testProductCategory1 = new ProductCategory("Flower",
            "Plant",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");
    private ProductCategory testProductCategory2 = new ProductCategory("Cactus",
            "Plant",
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.");

    private Supplier testSupplier1 = new Supplier("Super Flowers Kft.", "Small family business with quality plants");
    private Supplier testSupplier2 = new Supplier("King Of Cactus Kft.", "Main priority is cacti, but also sell other plants");

    private Product testProduct1 = new Product("Name",
            10.1f,
            "USD",
            "name",
            testProductCategory1,
            testSupplier1);
    private Product testProduct2 = new Product("Spikealousless Cactus",
            10,
            "USD",
            "Smol cactus",
            testProductCategory2,
            testSupplier2);

    @BeforeEach
    void clearDataBase() {
        testProductDao.removeAll();
        testProductCategoryDao.removeAll();
        testSupplierDao.removeAll();
        testProductCategoryDao.add(testProductCategory1);
        testProductCategoryDao.add(testProductCategory2);
        testSupplierDao.add(testSupplier1);
        testSupplierDao.add(testSupplier2);
    }

    @Test
    void testAdd() {
        testProductCategory1.setId(1);
        testSupplier1.setId(1);
        testProduct1.setId(1);
        testProductDao.add(testProduct1);
        assertTrue(testProductDao.getAll().size() == 1);
        assertTrue(testProductDao.getAll().contains(testProduct1));
    }

    @Test
    void testFindByExistingId() {
        testProductCategory1.setId(1);
        testSupplier1.setId(1);
        testProductDao.add(testProduct1);
        testProductCategory2.setId(2);
        testSupplier2.setId(2);
        testProductDao.add(testProduct2);
        testProduct2.setId(2);
        assertEquals(testProduct2, testProductDao.find(2));
    }

    @Test
    void testRemoveByExistingId() {
        testProductCategory1.setId(1);
        testSupplier1.setId(1);
        testProductDao.add(testProduct1);
        testProductCategory2.setId(2);
        testSupplier2.setId(2);
        testProductDao.add(testProduct2);
        testProductDao.remove(1);
        testProduct2.setId(2);
        assertTrue(testProduct2.equals(testProductDao.find(2)));
        assertEquals(1, testProductDao.getAll().size());
    }

    @Test
    void testRemoveByNotExistingId() {
        testProductCategory1.setId(1);
        testSupplier1.setId(1);
        testProductDao.add(testProduct1);
        testProductCategory2.setId(2);
        testSupplier2.setId(2);
        testProductDao.add(testProduct2);
        testProductDao.remove(3);
        testProduct1.setId(1);
        testProduct2.setId(2);
        assertTrue(areProductsMatching(testProduct1, testProductDao.find(1)));
        assertTrue(areProductsMatching(testProduct2, testProductDao.find(2)));
        assertEquals(2, testProductDao.getAll().size());
    }

    @Test
    void testGetBySupplier() {
        testProductCategory1.setId(1);
        testSupplier1.setId(1);
        testProductDao.add(testProduct1);
        testProductCategory2.setId(2);
        testSupplier2.setId(2);
        testProductDao.add(testProduct2);
        testProduct1.setId(1);
        assertEquals(new ArrayList<>(Arrays.asList(testProduct1)), testProductDao.getBy(testSupplier1));
    }

    @Test
    void testGetByProductCategory() {
        testProductCategory1.setId(1);
        testSupplier1.setId(1);
        testProductDao.add(testProduct1);
        testProductCategory2.setId(2);
        testSupplier2.setId(2);
        testProductDao.add(testProduct2);
        testProduct1.setId(1);
        assertEquals(new ArrayList<>(Arrays.asList(testProduct1)), testProductDao.getBy(testProductCategory1));
    }

    @Test
    void testRemoveAll() {
        testProductCategory1.setId(1);
        testSupplier1.setId(1);
        testProductDao.add(testProduct1);
        testProductCategory2.setId(2);
        testSupplier2.setId(2);
        testProductDao.add(testProduct2);
        assertNotEquals(testProductDao.getAll(), new ArrayList<Product>());
        testProductDao.removeAll();
        assertEquals(testProductDao.getAll(), new ArrayList<Product>());
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