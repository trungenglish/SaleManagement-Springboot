package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.service.ProductService;

import com.example.SaleManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ProductService productService;

    // enpoint ssr
    @GetMapping("/showFormPurchaseOrder")
    public String showPurchaseOrder(Model model) {
        PurchaseOrder purchaseOrder = new PurchaseOrder();
        purchaseOrder.setDateOrderPurchase(LocalDateTime.now());
        model.addAttribute("purchaseOrder",purchaseOrder);
        model.addAttribute("products",productService.getListProduct());
        model.addAttribute("suppliers",supplierService.getListSupplier());
        return "/layouts/importGoods/layout_item_purchase_order";
    }
}
