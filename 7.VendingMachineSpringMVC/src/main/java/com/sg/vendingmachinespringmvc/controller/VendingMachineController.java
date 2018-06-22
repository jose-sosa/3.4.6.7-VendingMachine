/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.vendingmachinespringmvc.controller;

import com.sg.vendingmachinespringmvc.dao.exceptions.VendingMachinePersistenceException;
import com.sg.vendingmachinespringmvc.model.Item;
import com.sg.vendingmachinespringmvc.service.VendingMachineService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author josesosa
 */
@Controller
public class VendingMachineController {
    VendingMachineService service;
    
    @Inject
    public VendingMachineController(VendingMachineService service) {
        this.service = service;
       
    }
    
    @RequestMapping(value = {"/","index"}, method = RequestMethod.GET)
    public String displayItems(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        // Get all the Contacts from the DAO
        List<Item> itemList = service.getAllSelections();
 
        model.addAttribute("itemList", itemList);

        // Return the logical name of our View component
        return "index";
    }
    
    @RequestMapping(value = "/selectItem", method = RequestMethod.GET)
    public String selectItem(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        String itemIdParameter = request.getParameter("id");
        int itemId = Integer.parseInt(itemIdParameter);
        Item temp = service.getSelectionByName(itemId);
        double temp3 =service.getCurrentCash();
        BigDecimal monetaryDisplay = new BigDecimal(temp3).setScale(2, RoundingMode.CEILING);
        List<Item> itemList = service.getAllSelections();
        service.resetMessage();
        String messageString = service.getMessage();
        service.setCurrentItem(temp);
 
        model.addAttribute("itemList", itemList);
        model.addAttribute("updatedMoney",monetaryDisplay);
        model.addAttribute("item",temp.getItemNumber());
        model.addAttribute("messageString", messageString);
        return "index"; 
    }
    
    @RequestMapping(value = "/addMoney", method = RequestMethod.GET)
    public String addMoney(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        String itemIdParameter = request.getParameter("id");
        double increment = Double.parseDouble(itemIdParameter);
        service.setCurrentCash(increment/100);
        double temp = service.getCurrentCash();
        BigDecimal monetaryDisplay = new BigDecimal(temp).setScale(2, RoundingMode.CEILING);
        Item temp3 = service.getCurrentItem();
        List<Item> itemList = service.getAllSelections();
        service.resetMessage();
        String messageString = service.getMessage();
        
 
        model.addAttribute("itemList", itemList);
        model.addAttribute("item",temp3.getItemNumber());
        model.addAttribute("updatedMoney",monetaryDisplay);
        model.addAttribute("messageString", messageString);
        return "index"; 
    }
    
    @RequestMapping(value = "/makePurchase", method = RequestMethod.GET)
    public String makePurchase(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        // redundant, I know
        service.makePurchase();
        double temp = service.getCurrentCash();
        BigDecimal monetaryDisplay = new BigDecimal(temp).setScale(2, RoundingMode.CEILING);
        Item temp3 = service.getCurrentItem();
        List<Item> itemList = service.getAllSelections();
        
        String messageString = service.getMessage();
 
        model.addAttribute("itemList", itemList);
        model.addAttribute("item",temp3.getItemNumber());
        model.addAttribute("updatedMoney",monetaryDisplay);
        model.addAttribute("messageString", messageString);
        
        return "index"; 
    }
    
    @RequestMapping(value = "/changeReturn", method = RequestMethod.GET)
    public String changeReturn(HttpServletRequest request, Model model) throws VendingMachinePersistenceException {
        service.calculateChange();
        String changeBreakDown = service.getChangeBreakDown();
        service.reset();
        double temp = service.getCurrentCash();
        BigDecimal monetaryDisplay = new BigDecimal(temp).setScale(2, RoundingMode.CEILING);
        Item temp3 = service.getCurrentItem();
        String emptyString="";
        List<Item> itemList = service.getAllSelections();
        
        String messageString = service.getMessage();
        
 
        model.addAttribute("itemList", itemList);
        model.addAttribute("item",emptyString);
        model.addAttribute("updatedMoney",monetaryDisplay);
        model.addAttribute("messageString", messageString);
        model.addAttribute("changeBreakDown",changeBreakDown);
        
        return "index"; 
    }
    
    
}
