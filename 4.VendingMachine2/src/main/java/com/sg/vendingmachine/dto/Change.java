/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.dto;

import java.math.BigDecimal;

/**
 *
 * @author josesosa
 */
public class Change {
    private BigDecimal change;
    private final BigDecimal input;
    private final BigDecimal cost;
    private final BigDecimal changeBeforeBreakDown;
    private int tempHolder;
    
    public Change(BigDecimal input, BigDecimal cost) {
        this.input = input;
        this.cost = cost;
        this.changeBeforeBreakDown = input.subtract(cost);
    }
    
    public String calculateChange(VendingMachineDetails vmd) {
        String tempString = "";
        change  = input.subtract(cost);
        int[] intArray = vmd.getIntArray();
        Denominations[] denomArr = Denominations.values();
        
        for (int i = 0; i  < intArray.length ; i++){
            tempString += calculateAmount(denomArr[i],intArray[i]);
            intArray[i] = tempHolder; 
        }
        
        vmd.setIntArray(intArray);
        
        return "You're change in denominations and quantity is displayed below \n" + changeBeforeBreakDown + "\n" +  tempString;
    } 
    
    public String calculateAmount(Denominations den,int amountInmachine){
        int tempQuant = 0;
        while(change.compareTo(den.description) >=0 && amountInmachine  > 0){
            tempQuant++;
            amountInmachine--;
            change = change.subtract(den.description);
        }
        
        tempHolder = amountInmachine;
        return den + " :  " + tempQuant + "\n";
    }
    
}

//            for (Denominations denom : Denominations.values() ){
//                calculateAmount(denom,tempQuantity);
//                tempQuantity=0;
//            }
// ----------------------------------------------------------------------------
//        public void calculateAmount(Denominations den, int tempQuant){
//        
//            while(change.compareTo(den.description) >=0 ){
//                tempQuant++; 
//                change = change.subtract(den.description);
//            }
//
//            tempString += (den + " :  " + tempQuant + "\n");
//        }
// ----------------------------------------------------------------------------
//    public String calculateAmount(Denominations den,int amountInmachine){
//        
//        while(change.compareTo(den.description) >=0 && amountInmachine  > 0){
//            den.quantity++;
//            amountInmachine--;
//            change = change.subtract(den.description);
//        }
//        tempHolder = amountInmachine;
//        return den + " :  " + den.quantity + "\n";
//        
//    }


