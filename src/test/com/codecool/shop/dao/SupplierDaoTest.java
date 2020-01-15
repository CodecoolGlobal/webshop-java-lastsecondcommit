package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.JDBC.JDBCHelper;
import com.codecool.shop.dao.implementation.Mem.SupplierDaoMem;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {
    //private SupplierDao testedSupplierDao = SupplierDaoJDBC.getInstance();
    private SupplierDao testedSupplierDao = SupplierDaoMem.getInstance();
    private Supplier testSupplier = new Supplier("testSupplier", "testDescription");
    private Supplier testSupplier1 = new Supplier("testSupplier1", "testDescription1");
    private Supplier testSupplier2 = new Supplier("testSupplier2", "testDescription2");

    @BeforeEach
    void clearDataBase() {
        Connection connection = JDBCHelper.getConnection();
        String query = "DELETE FROM product";

        try (Statement statement = connection.createStatement())
        {
            statement.executeUpdate(query); // Execute update
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
        Supplier resultSupplier = testSupplier;
        resultSupplier.setId(1);
        List<Supplier> suppliers = testedSupplierDao.getAll();
        boolean isExpectedDataInSuppliers = false;
        for(Supplier suplier:suppliers){
            if (areSuppliersMatching(suplier, resultSupplier)){
                isExpectedDataInSuppliers = true;
            }
        }
        assertTrue(isExpectedDataInSuppliers);
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
        testedSupplierDao.add(testSupplier);
        testedSupplierDao.add(testSupplier1);
        testedSupplierDao.add(testSupplier2);
        testedSupplierDao.remove(2);
        assertEquals(testedSupplierDao.getAll().size(), 2);
        assertFalse(testedSupplierDao.getAll().contains(testSupplier1));
    }

    @Test
    void testGetAllGivesBackAllTheResults() {
        testedSupplierDao.add(testSupplier);
        Supplier resultSupplier = testSupplier;
        resultSupplier.setId(1);
        testedSupplierDao.add(testSupplier1);
        Supplier resultSupplier1 = testSupplier1;
        resultSupplier.setId(2);
        Supplier resultSupplier2 = testSupplier2;
        boolean isTestSupplierInSuppliers = false;
        boolean isTestSupplier1InSuppliers = false;
        boolean isTestSupplier2InSuppliers = false;
        List<Supplier> suppliers = testedSupplierDao.getAll();
        for(Supplier suplier:suppliers){
            if (areSuppliersMatching(suplier, resultSupplier)){
                isTestSupplierInSuppliers = true;
            }
            if (areSuppliersMatching(suplier, resultSupplier1)){
                isTestSupplier1InSuppliers = true;
            }
            if (areSuppliersMatching(suplier, resultSupplier2)){
                isTestSupplier2InSuppliers = true;
            }
        }
        assertTrue(isTestSupplierInSuppliers && isTestSupplier1InSuppliers && isTestSupplier2InSuppliers);
    }

    private boolean areSuppliersMatching(Supplier supplier1, Supplier supplier2){
        return  supplier1.getId() == supplier2.getId()
                && supplier1.getName() == supplier2.getName()
                && supplier1.getDescription() == supplier2.getDescription();
    }
}