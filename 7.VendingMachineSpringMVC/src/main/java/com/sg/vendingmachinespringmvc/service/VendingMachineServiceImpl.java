/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.service;

import com.sg.vendingmachinespringmvc.dao.VendingMachineDao;
import com.sg.vendingmachinespringmvc.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Change;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.exceptions.VendingMachineInsufficientFundsException;
import com.sg.vendingmachinespringmvc.service.exceptions.VendingMachineNoItemInventoryException;
import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

/**
 *
 * @author josesosa
 */
public class VendingMachineServiceImpl implements VendingMachineService {
    VendingMachineDao dao;
    Item currentItem;
    double currentCash=0;
    Change myChange = new Change();
    String returnString = "";
    String changeBreakdown="";
    
    
    
    @Inject
    public VendingMachineServiceImpl(VendingMachineDao dao) {
        this.dao = dao;  
    }

    @Override
    public List<Item> getAllSelections() throws VendingMachinePersistenceException {
        return dao.getAllSelections();
    }
    
    @Override
    public Item getSelectionByName(int temp) throws VendingMachinePersistenceException {
        return dao.getSelectionByName(temp);
    }

    @Override
    public void purchaseSelection(Item selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException, VendingMachinePersistenceException {
    validatePurchase(selection, balance);
        
        selection.setInventory(selection.getInventory() - 1);
        
        try{
           dao.updateInventory(selection); 
        } catch (VendingMachinePersistenceException e){
            throw new VendingMachinePersistenceException ("Unable to update inventory", e);
        }
    }

    @Override
    public void validatePurchase(Item selection, BigDecimal balance) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        
        
        if (selection.getInventory() == 0 ){
            
            throw new VendingMachineNoItemInventoryException("ERROR: Item not available");
            
        }
        
        if (selection.getCost().doubleValue() > balance.doubleValue() ){
            
            throw new VendingMachineInsufficientFundsException(
                    "ERROR: Insufficient Funds.");
        }
    }

    @Override
    public void setCurrentItem(Item temp) {
        currentItem = temp;
    }
    
    @Override
    public Item getCurrentItem(){
        return currentItem;
    }
    
    @Override
    public double setCurrentCash(double temp){
        return currentCash += temp;
    }
    
    @Override
    public double getCurrentCash(){
        return currentCash;
    }
    
    @Override
    public void makePurchase(){
        
        if(currentItem == null){
            returnString = "You have not made a selection";
        }
        else if (currentItem.getInventory()<1){
            returnString = "Item is not in stock";
        }
        else if(currentItem.getCost().doubleValue() > currentCash){
            returnString = "Not enough money";
        }else{
            try {
                dao.updateInventory(currentItem);
            } catch (VendingMachinePersistenceException ex) {
                
            }
            currentCash-=currentItem.getCost().doubleValue();
            returnString = "Thank You";
        }
        
    }
    
    @Override
    public String getMessage(){
        return returnString;
    }
    
    @Override
    public void resetMessage(){
        returnString = "";
    }
    
    @Override
    public void reset(){
        returnString = "";
        currentItem=null;
        currentCash=0;
    }
    
    @Override
    public void calculateChange(){
        changeBreakdown = myChange.calculateChange(currentCash);
    }
    
    @Override
    public String getChangeBreakDown(){
        return changeBreakdown;
    }
    
    
    
}
