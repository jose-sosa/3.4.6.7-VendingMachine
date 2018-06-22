/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.exceptions.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.exceptions.VendingMachineNoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author josesosa
 */
public interface VendingMachineService {
    public List<Item> getAllSelections() throws VendingMachinePersistenceException;
    
    public Item getSelectionByName(int temp) throws VendingMachinePersistenceException;
    
    public void purchaseSelection(Item selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException ;
    
    public void validatePurchase(Item selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException;

    public void setCurrentItem(Item temp);
    
    public Item getCurrentItem();
    
    public double setCurrentCash(double temp);
    
    public double getCurrentCash();

    public void makePurchase();
    
    public String getMessage();
    
    public void resetMessage();

    public void reset();

    public void calculateChange();
    
    public String getChangeBreakDown();
    
}
