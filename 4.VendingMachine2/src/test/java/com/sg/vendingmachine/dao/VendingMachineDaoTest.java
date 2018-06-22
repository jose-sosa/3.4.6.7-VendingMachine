/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;

/**
 *
 * @author josesosa
 */
public class VendingMachineDaoTest {
    
    VendingMachineDao dao = new VendingMachineDaoImpl();
    
    public VendingMachineDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllSelections method, of class VendingMachineDao.
     */
    @org.junit.Test
    public void testGetAllSelections() throws Exception {
        dao.getAllSelections();
        assertEquals(5, dao.getAllSelections().size());
    }

    /**
     * Test of getSelectionByName method, of class VendingMachineDao.
     */
    @org.junit.Test
    public void testGetSelectionByName() throws Exception {
    }

    /**
     * Test of writeInventory method, of class VendingMachineDao.
     */
    @org.junit.Test
    public void testWriteInventory() throws Exception {
    }

    /**
     * Test of getAllVendingMachineDetails method, of class VendingMachineDao.
     */
    
    
}
