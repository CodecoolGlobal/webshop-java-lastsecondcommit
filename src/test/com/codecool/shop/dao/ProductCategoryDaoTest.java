//package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.Mem.ProductCategoryDaoMem;
import com.codecool.shop.model.ProductCategory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class ProductCategoryDaoTest {
    // ProductCategoryDaoMem.getInstance() OR ProductCategoryDaoJDBC.getInstance()
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoMem.getInstance();
    private ProductCategory testProductCategory1 = new ProductCategory(
            "Cactus",
            "Plant",
            "Little description");
    private ProductCategory testProductCategory2 = new ProductCategory(
            "Flower",
            "Plant",
            "my new description");
    private ProductCategory testProductCategory3 = new ProductCategory(
            "Little plant",
            "Izé", "Valami új karakterék");

    @BeforeEach
    void clearDataBase() {
        productCategoryDao.removeAll();
    }

    @Test
    void testAddCorrectProductCategorySavedInDataStore() {
        productCategoryDao.add(testProductCategory1);
        testProductCategory1.setId(1);
        assertEquals(productCategoryDao.getAll().size(), 1);
        assertTrue(productCategoryDao.getAll().contains(testProductCategory1));
    }

    @Test
    void testFindByExistingId() {
        productCategoryDao.add(testProductCategory1);
        productCategoryDao.add(testProductCategory2);
        productCategoryDao.add(testProductCategory3);
        testProductCategory3.setId(3);
        assertEquals(testProductCategory3, productCategoryDao.find(3));
    }

    @Test
    void testFindNotExistingId() {
        productCategoryDao.add(testProductCategory1);
        productCategoryDao.add(testProductCategory2);
        assertNull(productCategoryDao.find(4));
    }

    @Test
    void testRemoveByExistingId() {
        productCategoryDao.add(testProductCategory1);
        productCategoryDao.add(testProductCategory2);
        productCategoryDao.add(testProductCategory3);
        productCategoryDao.remove(2);
        assertEquals(productCategoryDao.getAll().size(), 2);
        assertFalse(productCategoryDao.getAll().contains(testProductCategory2));
    }

    @Test
    void testGetAllWithProductCategoriesInDao() {
        productCategoryDao.add(testProductCategory1);
        testProductCategory1.setId(1);
        productCategoryDao.add(testProductCategory2);
        testProductCategory2.setId(2);
        productCategoryDao.add(testProductCategory3);
        testProductCategory3.setId(3);
        assertEquals(new ArrayList<>(Arrays.asList(testProductCategory1, testProductCategory2, testProductCategory3)), productCategoryDao.getAll());
    }

    @Test
    void testGetAllWithoutProductCategoriesInDao() {
        assertEquals(new ArrayList<>(), productCategoryDao.getAll());
    }

    @Test
    void testRemoveAll() {
        productCategoryDao.add(testProductCategory1);
        productCategoryDao.add(testProductCategory2);
        productCategoryDao.add(testProductCategory3);
        assertNotEquals(new ArrayList<ProductCategory>(), productCategoryDao.getAll());
        productCategoryDao.removeAll();
        assertEquals(new ArrayList<ProductCategory>(), productCategoryDao.getAll());
    }
}