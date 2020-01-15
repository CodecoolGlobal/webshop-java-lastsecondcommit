package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.JDBC.JDBCHelper;
import com.codecool.shop.dao.implementation.Mem.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {
    //private SupplierDao testedSupplierDao = SupplierDaoJDBC.getInstance();
    private SupplierDao testedSupplierDao = SupplierDaoMem.getInstance();
    private Supplier testSupplier = new Supplier("testSupplier", "testDescription");

    @BeforeEach
    void clearDataBase() {
        Connection connection = JDBCHelper.getConnection();
        String query = "DELETE FROM product";

        try (Statement statement = connection.createStatement())
        {
            statement.executeQuery(query); // Execute update
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testAddIncreasesDataStoreSizeByOne() {
        int size = testedSupplierDao.getAll().size();
        testedSupplierDao.add(testSupplier);
        assertTrue(testedSupplierDao.getAll().size() == size + 1);
    }

    @Test
    void testAddCorrectData(){
        testedSupplierDao.add(testSupplier);
        Supplier resultSupplier = testedSupplierDao.find(1);
        testSupplier.setId(1);
        assertTrue(areSuppliersMatching(testSupplier, resultSupplier));
    }

    @Test
    void testFindByExistingId() {
        testedSupplierDao.add(testSupplier);
        Supplier resultSupplier = testedSupplierDao.find(1);
        testSupplier.setId(1);
        assertTrue(areSuppliersMatching(testSupplier, resultSupplier));
    }

    @Test
    void testRemoveByExistingId() {
    }

    private boolean areSuppliersMatching(Supplier supplier1, Supplier supplier2){
        return  supplier1.getId() == supplier2.getId()
                && supplier1.getName() == supplier2.getName()
                && supplier1.getDescription() == supplier2.getDescription();
    }
}