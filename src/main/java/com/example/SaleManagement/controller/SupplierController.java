package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Supplier;
import com.example.SaleManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("suppliers",supplierService.getListSupplier());
        model.addAttribute("supplier",new Supplier());
        return "layouts/supplier/layout_list_supplier";
    }

    @PostMapping("/add")
    public String addSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierService.addSupplier(supplier);
        return "redirect:/suppliers/list";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("supId") Supplier supplier, Model model){
        model.addAttribute("supplier",supplierService.getSupplier(supplier.getIdSupplier()));
        return "layouts/supplier/layout_update_supplier";
    }

    @PostMapping("/update")
    public String updateSupplier(@ModelAttribute("supplier") Supplier supplier){
        supplierService.updateSupplier(supplier.getIdSupplier(),supplier);
        return "redirect:/suppliers/list";
    }

    @GetMapping("/delete")
    public String deleteSupplier(@RequestParam("supId") int idSup){
        supplierService.deleteSupplier(idSup);
        return "redirect:/suppliers/list";
    }
}
