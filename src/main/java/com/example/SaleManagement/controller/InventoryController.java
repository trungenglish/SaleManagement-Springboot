package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Inventory;
import com.example.SaleManagement.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/inventory")
public class InventoryController {
    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("inventories", inventoryService.getListInventory());
        return "layouts/inventory/layout_list_inventory";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("inventoryId") int id, Model model) {
        model.addAttribute("inventory", inventoryService.getInventory(id));
        return "layouts/inventory/layout_update_inventory";
    }

    @PostMapping("/update")
    public String updateQuantity(@ModelAttribute("inventory") Inventory request) {
        inventoryService.updateQuantity(request,request.getIdInventory());
        return "redirect:/inventory/list";
    }
}
