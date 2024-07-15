package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImportGoodsRepository extends JpaRepository<PurchaseOrder, Integer> {
}
