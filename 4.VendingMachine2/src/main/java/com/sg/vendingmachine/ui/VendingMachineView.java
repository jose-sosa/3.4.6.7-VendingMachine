/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.ui;

import com.sg.vendingmachine.dto.Selection;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author josesosa
 */
public class VendingMachineView {
    UserIO io;
    
    
    public VendingMachineView(UserIO io) {
        this.io = io;
    }
    
    public int getMenuSelection() {
        
        io.print("How would you like to proceed?");
        return io.readInt("1. Make a selection and purchase \n2. Sort By Input \n3. Exit", 1, 3);
    }
    
    public void displaySelectionList(List<Selection> selectionList) {
        
        for (Selection currentSelection : selectionList) {
            io.print(currentSelection.getSelectionName() + " -  " +
                    currentSelection.getCost() + " : "  +
                    currentSelection.getInventory());
        }
        io.readString("Please hit enter to continue.");
    }
    
    public BigDecimal getMonetaryInput(){ 
        
        
        Double a  = io.readDouble("How much money would you like to put in? ",0,1000000);
        BigDecimal cash = new BigDecimal(a);
        
        return cash;
    }
    
    public String getSelection(){
        return io.readString("What would you like to purchase? Exact name please. Case Sensitive.");
    }
   
    public void sayHello() {
        
        io.print("Welcome to Sosa's Vending Machine!");
      
    }
    
    public void displayString(String string) {
        
        io.print(string);
      
    }
    
    public void sayGoodBye() {
        
        io.print("Thank you for vending with us!");
      
    }
    
    public void displaySuccesfulPurchase() {
        
        io.print("Please wait a moment for your item to be vended");
        
    }
    
    public void displayUnsuccesfulPurchase1() {
        
        io.print("I'm sorry, you cannot purchase that item");
        
    }
    
    public void displayUnsuccesfulPurchase2() {
        
        io.print("I'm sorry, that selection is out of stock.");
        
    }

    public void displayBalance(BigDecimal balance) {
        io.print("Your balance is $" + balance);
    }
    
    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
    public BigDecimal promptUserForCost() {
        Double a = io.readDouble("What Cost would you like to sort by, less than or equal to?", 0, 1000);
        BigDecimal cash = new BigDecimal(a);
        return cash;
    }
    
    public int promptUserForInventory() {
        int inven = io.readInt("What Inventory would you like to sort by, greater than?", 0, 1000);
        return inven;
    }
    
    
}
