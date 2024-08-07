package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Integer>{
    PurchaseOrder findByIdPurchaseOrder(int id);
}
