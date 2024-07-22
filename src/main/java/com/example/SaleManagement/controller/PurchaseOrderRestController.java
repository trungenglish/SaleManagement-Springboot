package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.service.PurchaseOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/purchase-orders")
public class PurchaseOrderRestController {

    @Autowired
    private PurchaseOrderService purchaseOrderService;

    //endpoint csr
    @PostMapping("/add")
    public ResponseEntity<Void> addPurchaseOrder(@RequestBody PurchaseOrder request) {
        purchaseOrderService.addPurchaseOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}

