package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Order;
import com.example.SaleManagement.model.OrderDetail;
import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.repository.OrdersRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrdersRepository ordersRepository;

    public Order getOrder(int id){
        return ordersRepository.findByIdOrder(id);
    }

    public List<Order> getListInvoice(){
        return ordersRepository.findAll();
    }

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
