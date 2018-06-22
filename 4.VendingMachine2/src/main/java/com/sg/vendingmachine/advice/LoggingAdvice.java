/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine.advice;

import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.sg.vendingmachine.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.sg.vendingmachine.service.VendingMachineNoItemInventoryException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author josesosa
 */
public class LoggingAdvice {
    VendingMachineAuditDao auditDao;
    int insufficientCount = 0;
    int inventoryCount = 0;
    int count = 0;
    public LoggingAdvice(VendingMachineAuditDaoImpl auditDao) {
        this.auditDao = auditDao;
    }
    
    public void createAuditEntry(JoinPoint jp, Throwable ex) throws VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException  {
        if(ex.toString().equals("| No Item In Inventory Exception")){
            count = inventoryCount++;
        }else{
            count = insufficientCount++;
        }
        
//        if (ex instanceof VendingMachineInsufficientFundsException ){
//            count = inventoryCount++;
//        }else{
//            count = inventoryCount++;
//        }
    
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + " : " + ex.toString() + " (" + count + ") ";
       
//        for (Object currentArg : args) {
//            auditEntry += currentArg;
//            
//        }
        auditEntry += args[0];
        auditEntry += " | The balance was: $" + args[1];
        
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
               "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
    
}

