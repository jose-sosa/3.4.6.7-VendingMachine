/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

import java.math.BigDecimal;

/**
 *
 * @author josesosa
 */
public class Item {
    private final  String selectionName;
    private BigDecimal cost;
    private int inventory;
    private final int itemNumber;
    
    public Item (String name, int itemNumber, int inventory,int cost) {
        this.selectionName = name;
        this.itemNumber = itemNumber;
        this.inventory=inventory;
        this.cost= new BigDecimal(cost);
    }

    public String getSelectionName() {
        return selectionName;
    }

    public BigDecimal getCost() {
        return cost;
    } 

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }
    
    public int getItemNumber() {
        return itemNumber;
    }

    
    
}
