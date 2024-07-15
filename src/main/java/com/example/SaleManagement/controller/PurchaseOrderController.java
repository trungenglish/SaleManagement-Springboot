package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.model.Supplier;
import com.example.SaleManagement.service.ProductService;
import com.example.SaleManagement.service.PurchaseOrderService;
import com.example.SaleManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


@Controller
@RequestMapping("/purchase-orders")
public class PurchaseOrderController {
    @Autowired
    private PurchaseOrderService purchaseOrderService;
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

    @GetMapping("/add")
    public String addPurchaseOrder(@ModelAttribute("purchaseOrder") PurchaseOrder purchaseOrder) {
        purchaseOrderService.addPurchaseOrder(purchaseOrder);
        return "redirect:/purchase-orders/showFormPurchaseOrder";
    }

//    @GetMapping("/suppliers")
//    public ResponseEntity<List<Supplier>> getAllSuppliers() {
//        List<Supplier> suppliers = supplierService.getListSupplier();
//        return new ResponseEntity<>(suppliers, HttpStatus.OK);
//    }
//
//    @PostMapping("/add")
//    public ResponseEntity<PurchaseOrder> addPurchaseOrder(@RequestBody PurchaseOrder request) {
//        purchaseOrderService.addPurchaseOrder(request);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
}
