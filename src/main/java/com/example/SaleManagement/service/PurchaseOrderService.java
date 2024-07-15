package com.example.SaleManagement.service;

import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.model.PurchaseOrderItem;
import com.example.SaleManagement.repository.PurchaseOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseOrderService {
    @Autowired
    private PurchaseOrderRepository purchaseOrderRepository;

    public void addPurchaseOrder(PurchaseOrder request){

        for (PurchaseOrderItem item : request.getItems()) {// vòng lặp này sẽ chạy qua từng item trong danh sách item của purchaseOrder
            item.setPurchaseOrder(request);// set purchaseOrder cho item
        }
        request.setTotalPurchase(request.getItems().stream()
                .mapToDouble(item -> item.getPriceOrderPurchase() * item.getQuantityItemOrderPurchase())
                .sum());
        purchaseOrderRepository.save(request);
    }
}
