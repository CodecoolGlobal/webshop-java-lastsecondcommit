package com.codecool.shop.dao;
import com.codecool.shop.dao.implementation.JDBC.ProductCategoryDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.ProductDaoJDBC;
import com.codecool.shop.dao.implementation.JDBC.SupplierDaoJDBC;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SupplierDaoTest {
    private ProductCategoryDao productCategoryDao = ProductCategoryDaoJDBC.getInstance();
    private ProductDao productDao = ProductDaoJDBC.getInstance();
    private SupplierDao supplierDao = SupplierDaoJDBC.getInstance();
    //private SupplierDao testedSupplierDao = SupplierDaoMem.getInstance();
    private Supplier testSupplier = new Supplier("testSupplier", "testDescription");
    private Supplier testSupplier1 = new Supplier("testSupplier1", "testDescription1");
    private Supplier testSupplier2 = new Supplier("testSupplier2", "testDescription2");

    @BeforeEach
    void clearDataBase() {
        productDao.removeAll();
        productCategoryDao.removeAll();
        supplierDao.removeAll();
    }

    @Test
    void testAddIncreasesDataStoreSizeByOne() {
        int size = supplierDao.getAll().size();
        supplierDao.add(testSupplier);
        assertEquals(supplierDao.getAll().size(), size + 1);
    }

    @Test
    void testAddCorrectData(){
        supplierDao.add(testSupplier);
        Supplier resultSupplier = testSupplier;
        resultSupplier.setId(1);
        List<Supplier> suppliers = supplierDao.getAll();
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
        supplierDao.add(testSupplier);
        Supplier resultSupplier = supplierDao.find(1);
        testSupplier.setId(1);
        assertTrue(areSuppliersMatching(testSupplier, resultSupplier));
    }

    @Test
    void testRemoveByExistingId() {
        supplierDao.add(testSupplier);
        supplierDao.add(testSupplier1);
        supplierDao.add(testSupplier2);
        supplierDao.remove(2);
        assertEquals(supplierDao.getAll().size(), 2);
        assertFalse(supplierDao.getAll().contains(testSupplier1));
    }

    private boolean areSuppliersMatching(Supplier supplierFirst, Supplier supplierSecond){
        return  supplierFirst.getId() == supplierSecond.getId()
                && supplierFirst.getName().equals(supplierSecond.getName())
                && supplierFirst.getDescription().equals(supplierSecond.getDescription());
    }
}