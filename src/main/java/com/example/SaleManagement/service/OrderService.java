package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Order;
import com.example.SaleManagement.model.OrderDetail;
import com.example.SaleManagement.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    @Transactional
    public void addOrder(Order request){
        if (request.getItems() == null) {
            throw new IllegalArgumentException("Order items cannot be null");
        }
        for (OrderDetail item : request.getItems()) {
            item.setOrder(request);
        }
        request.setTotal(request.getItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum()) ;
        ordersRepository.save(request);
    }
}
