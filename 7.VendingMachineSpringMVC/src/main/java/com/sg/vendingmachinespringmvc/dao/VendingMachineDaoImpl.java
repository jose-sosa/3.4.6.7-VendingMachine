/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author josesosa
 */
public class VendingMachineDaoImpl implements VendingMachineDao {
    private final Map<Integer, Item> inventory = new HashMap<>();
    
    public VendingMachineDaoImpl() {
        Item item1 = new Item("Cookie",1,10,2);
        this.inventory.put(item1.getItemNumber(),item1);
        
        Item item2 = new Item("Flour",2,10,2);
        this.inventory.put(item2.getItemNumber(),item2);
        
        Item item3 = new Item("Seed",3,10,2);
        this.inventory.put(item3.getItemNumber(),item3);
        
        Item item4 = new Item("Cookie",4,10,2);
        this.inventory.put(item4.getItemNumber(),item4);
        
        Item item5 = new Item("Flour",5,10,2);
        this.inventory.put(item5.getItemNumber(),item5);
        
        Item item6 = new Item("Seed",6,10,2);
        this.inventory.put(item6.getItemNumber(),item6);
        
        Item item7 = new Item("Cookie",7,10,2);
        this.inventory.put(item7.getItemNumber(),item7);
        
        Item item8 = new Item("Flour",8,10,2);
        this.inventory.put(item8.getItemNumber(),item8);
        
        Item item9 = new Item("Seed",9,10,2);
        this.inventory.put(item9.getItemNumber(),item9);
    }
    
    
    
    @Override
    public List<Item> getAllSelections() throws VendingMachinePersistenceException{
       
        
        return new ArrayList<>(inventory.values());
    }
    
 

    @Override
    public Item getSelectionByName(int selectionNumber)  throws VendingMachinePersistenceException{
        
        return inventory.get(selectionNumber);
    }

    @Override
    public void updateInventory(Item temp) throws VendingMachinePersistenceException {
        temp.setInventory(temp.getInventory()-1);
        inventory.put(temp.getItemNumber(),temp);
    }
    
    
    
    

  
    
    
}
