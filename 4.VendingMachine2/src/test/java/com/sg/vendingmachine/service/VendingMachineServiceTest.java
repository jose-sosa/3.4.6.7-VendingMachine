/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.service;

import com.sg.vendingmachine.dto.Selection;
import java.math.BigDecimal;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author josesosa
 */
public class VendingMachineServiceTest {
    private VendingMachineService service;
    
    
    public VendingMachineServiceTest() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        this.service = ctx.getBean("serviceLayer", VendingMachineServiceImpl.class);
        
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
     * Test of purchaseSelection method, of class VendingMachineService.
     */
    @Test
    public void testIfPurchaseSelectionIsUpdatingTheInventory() throws Exception {
        int a = service.getSelectionByName("Cookie").getInventory();
        service.purchaseSelection(service.getSelectionByName("Cookie"),service.getSelectionByName("Cookie").getCost());
        assertEquals( (a - 1) ,service.getSelectionByName("Cookie").getInventory());
    }

    
    @Test
    public void testValidatePurchaseForAnItemInInventory() throws Exception {
        
        
        try {
            service.validatePurchase(service.getSelectionByName("Cookie"), new BigDecimal ("0.50"));
        fail("Expected VendingMachineInsufficientFundsException was not thrown.");
        } catch (VendingMachineInsufficientFundsException e) {
            return;
        }
        
    }
    
    @Test
    public void testValidatePurchaseForAnItemNotInInventory() throws Exception {
        Selection temp = service.getSelectionByName("Milk");
        temp.setInventory(0);
        
        try {
        service.validatePurchase(temp, new BigDecimal ("2.50"));
        fail("Expected VendingMachineNoItemInventoryException was not thrown.");
        } catch (VendingMachineNoItemInventoryException e) {
            return;
        }
        
    }
    
    
}
