package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.model.Supplier;
import com.example.SaleManagement.service.PurchaseOrderService;
import com.example.SaleManagement.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderRestController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;
    @Autowired
    private SupplierService supplierService;

    //endpoint csr
    @GetMapping("/suppliers")
    public ResponseEntity<List<Supplier>> getAllSuppliers() {
        List<Supplier> suppliers = supplierService.getListSupplier();
        return new ResponseEntity<>(suppliers, HttpStatus.OK);
    }

    //endpoint csr
    @PostMapping("/add")
    public ResponseEntity<Void> addPurchaseOrder(@RequestBody PurchaseOrder request) {
        purchaseOrderService.addPurchaseOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

