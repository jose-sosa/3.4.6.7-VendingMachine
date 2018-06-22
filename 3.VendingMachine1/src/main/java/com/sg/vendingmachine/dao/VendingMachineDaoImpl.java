/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dao;

import com.sg.vendingmachine.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachine.dto.Selection;
import com.sg.vendingmachine.dto.VendingMachineDetails;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author josesosa
 */
public class VendingMachineDaoImpl implements VendingMachineDao {
    private final Map<String, Selection> inventory = new HashMap<>();
    private VendingMachineDetails inventory2 = new VendingMachineDetails();
    public static final String INVENTORY = "inventory.txt";
    public static final String VMD = "vendingmachinedetails.txt";
    public static final String DELIMITER = "::";
    
    @Override
    public List<Selection> getAllSelections() throws VendingMachinePersistenceException{
        loadInventory();
        
        return new ArrayList<>(inventory.values());
    }
    
    @Override
    public VendingMachineDetails getAllVendingMachineDetails() throws VendingMachinePersistenceException{
        loadVendingMachineDetails();
        
        return inventory2;
    }

    @Override
    public Selection getSelectionByName(String selectionName)  throws VendingMachinePersistenceException{
        loadInventory();
        return inventory.get(selectionName);
    }
    
    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;
        
        try {

            scanner = new Scanner(new BufferedReader(new FileReader(INVENTORY)));
          
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Could not load the inventory into memory", e);
        }
    
        String currentLine;
        String[] currentTokens;
        // creates the hashmap with all
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            Selection currentSelection = new Selection(currentTokens[0]);
            currentSelection.setInventory(Integer.parseInt(currentTokens[2]));
            currentSelection.setCost(new BigDecimal(currentTokens[1]));
            inventory.put(currentSelection.getSelectionName(), currentSelection);
        }
        
        scanner.close();
    }
    
    @Override
    public void writeInventory() throws VendingMachinePersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(INVENTORY));
        } catch(IOException e) {
                throw new VendingMachinePersistenceException("Could not save inventory data.", e);
        }
        
        List<Selection> inventory =  this.getAllSelections();
        
        for(Selection selection: inventory) {
            out.println(selection.getSelectionName() + DELIMITER + selection.getCost() + DELIMITER + selection.getInventory());
            out.flush();
        }
        
        out.close();
    }
    
    private void loadVendingMachineDetails() throws VendingMachinePersistenceException {
        Scanner scanner;
        
        try {

            scanner = new Scanner(new BufferedReader(new FileReader(VMD)));
          
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("Could not load the vending into memory", e);
        }
    
        String currentLine;
        String[] currentTokens;
        
        while(scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            VendingMachineDetails currentSelection;
            
            currentSelection = new VendingMachineDetails();
            
            int[] intArray = currentSelection.getIntArray();
            
            // This integer array represents the quantities of the denominations
            // in order from largest denomination to smallest
            
            for(int i = 0; i <currentTokens.length; i++){
                intArray[i] = Integer.parseInt(currentTokens[i]);
            }
            
            currentSelection.setIntArray(intArray);
         
            inventory2 =  currentSelection;
        }
        scanner.close();
    }
    
    @Override
    public void writeVendingMachineDetails(VendingMachineDetails vmd) throws VendingMachinePersistenceException {
        
        PrintWriter out;
        
        try {
            out = new PrintWriter(new FileWriter(VMD));
        } catch(IOException e) {
                throw new VendingMachinePersistenceException("Could not save vending data.", e);
        }
        
        for (int intMem : vmd.getIntArray()){
            out.print(intMem + DELIMITER);
        } 
        
        out.flush();
        
        out.close();
    }
    
    @Override 
    public List<Selection> sortByInput(BigDecimal temp, int alsoTemp) throws VendingMachinePersistenceException {
        loadInventory();
        
        return inventory.values()
                .stream()
                .filter(s -> s.getCost().doubleValue() <= temp.doubleValue())
                .filter(s -> s.getInventory() > alsoTemp)
                .collect(Collectors.toList());
        
    }
    
}

// FROM LOADVENDINGMACHINEDETAILS
//            
//            
//      currentSelection.setBalance(Integer.parseInt(currentTokens[0]));
//      currentSelection.setHundredsQuant(Integer.parseInt(currentTokens[1]));
//      currentSelection.setFiftiesQuant(Integer.parseInt(currentTokens[2]));
//      currentSelection.setTwentiesQuant(Integer.parseInt(currentTokens[3]));
//      currentSelection.setTensQuant(Integer.parseInt(currentTokens[4]));
//      currentSelection.setFivesQuant(Integer.parseInt(currentTokens[5]));
//      currentSelection.setSinglesQuant(Integer.parseInt(currentTokens[6]));
//      currentSelection.setQuartersQuant(Integer.parseInt(currentTokens[7]));
//      currentSelection.setDimesQuant(Integer.parseInt(currentTokens[8]));
//      currentSelection.setNickelsQuant(Integer.parseInt(currentTokens[9]));
//      currentSelection.setPenniesQuant(Integer.parseInt(currentTokens[10]));
//            
//   --------------------------------------------------------------------------

// FROM WRITEVENDINGMACHINEDETAILS
//        VendingMachineDetails vendingmachinedetails = inventory2;
//        
//        out.println(vendingmachinedetails.getBalance() + DELIMITER + vendingmachinedetails.getHundredsQuant() + DELIMITER + vendingmachinedetails.getFiftiesQuant()
//        +  DELIMITER + vendingmachinedetails.getTwentiesQuant() + DELIMITER + vendingmachinedetails.getTensQuant() 
//        +  DELIMITER + vendingmachinedetails.getFivesQuant() + DELIMITER + vendingmachinedetails.getSinglesQuant()
//        + DELIMITER + vendingmachinedetails.getQuartersQuant() + DELIMITER + vendingmachinedetails.getDimesQuant()
//        + DELIMITER + vendingmachinedetails.getNickelsQuant() + DELIMITER + vendingmachinedetails.getPenniesQuant());
//        out.flush();
//        
//        out.print((int) vmd.getBalance() + DELIMITER);

