package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Order;
import com.example.SaleManagement.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
public class OrderRestController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/add")
    public ResponseEntity<Void> addOrder(@RequestBody Order request){
        orderService.addOrder(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
