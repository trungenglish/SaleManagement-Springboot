package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.Inventory;
import com.example.SaleManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoriesRepository extends JpaRepository<Inventory, Integer> {
    Inventory findByProduct(Product product);
    Inventory findByIdInventory(int id);
}
