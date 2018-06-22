/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.model;

/**
 *
 * @author josesosa
 */
public class Change {

    public String calculateChange(double currentCash) {
        int dollarCounter=0;
        int quarterCounter=0;
        int dimeCounter=0;
        int nickelCounter=0;
        
        
        double temp = 100 * currentCash;
        
        while(temp>=100){
            dollarCounter+=1;
            temp-=100;
        }
        while(temp>=25){
            quarterCounter+=1;
            temp-=25;
        }
        while(temp>=10){
            dimeCounter+=1;
            temp-=10;
        }
        while(temp>=5){
            nickelCounter+=1;
            temp-=5;
        }
        
        
        
        return "Testing";
        //return "Dollars : " + dollarCounter + "\n Quarters : " + quarterCounter + "\n Dimes : " + dimeCounter + "\n Nickels : " + nickelCounter ;
    }
    
}
