package com.example.SaleManagement.service;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.model.PurchaseOrderItem;
import com.example.SaleManagement.repository.PurchaseOrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    @Transactional
    public void addPurchaseOrder(PurchaseOrder request){
        if (request.getItems() == null) {
            throw new IllegalArgumentException("Purchase order items cannot be null");
        }
        for (PurchaseOrderItem item : request.getItems()) {// vòng lặp này sẽ chạy qua từng item trong danh sách item của purchaseOrder
            item.setPurchaseOrder(request);// set purchaseOrder cho item
        }
        request.setTotalPurchase(request.getItems().stream()
                .mapToDouble(item -> item.getPriceOrderPurchase() * item.getQuantityItemOrderPurchase())
                .sum());
        purchaseOrderRepository.save(request);
    }
}
