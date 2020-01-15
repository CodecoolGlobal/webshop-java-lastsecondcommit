package com.codecool.shop.dao;

import com.codecool.shop.dao.implementation.JDBC.JDBCHelper;
import com.codecool.shop.dao.implementation.JDBC.SupplierDaoJDBC;
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
    private SupplierDao testedSupplierDao = SupplierDaoJDBC.getInstance();
    //private SupplierDao testedSupplierDao = SupplierDaoMem.getInstance();
    private Supplier testSupplier = new Supplier("testSupplier", "testDescription");
    private Supplier testSupplier1 = new Supplier("testSupplier1", "testDescription1");
    private Supplier testSupplier2 = new Supplier("testSupplier2", "testDescription2");

    @BeforeEach
    void clearDataBase() {
        testedSupplierDao.removeAll();
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

    private boolean areSuppliersMatching(Supplier supplierFirst, Supplier supplierSecond){
        return  supplierFirst.getId() == supplierSecond.getId()
                && supplierFirst.getName().equals(supplierSecond.getName())
                && supplierFirst.getDescription().equals(supplierSecond.getDescription());
    }
}