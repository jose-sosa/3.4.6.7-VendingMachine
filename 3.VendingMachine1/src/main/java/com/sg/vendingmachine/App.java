/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachine;



import com.sg.vendingmachine.dao.VendingMachineAuditDao;
import com.sg.vendingmachine.controller.VendingMachineController;
import com.sg.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.sg.vendingmachine.dao.VendingMachineDao;
import com.sg.vendingmachine.dao.VendingMachineDaoImpl;
import com.sg.vendingmachine.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachine.service.VendingMachineService;
import com.sg.vendingmachine.service.VendingMachineServiceImpl;
import com.sg.vendingmachine.ui.UserIO;
import com.sg.vendingmachine.ui.UserIOImpl;
import com.sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author josesosa
 */
public class App {
    public static void main(String[] args) throws VendingMachinePersistenceException {
        UserIO iO = new UserIOImpl();
        VendingMachineView myView = new VendingMachineView(iO);
        VendingMachineDao myDao = new VendingMachineDaoImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoImpl();
        VendingMachineService myService = new VendingMachineServiceImpl(myDao, myAuditDao);
        VendingMachineController controller = new VendingMachineController(myService,  myView);
        controller.run();
        
    }
    
}
