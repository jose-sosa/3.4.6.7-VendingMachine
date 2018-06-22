/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.dao;

import com.sg.vendingmachinespringmvc.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import java.util.List;

/**
 *
 * @author josesosa
 */
public interface VendingMachineDao {
    public List<Item> getAllSelections() throws VendingMachinePersistenceException;
    public Item getSelectionByName(int selectionNumber) throws VendingMachinePersistenceException;
    public void updateInventory(Item temp) throws VendingMachinePersistenceException;

}
