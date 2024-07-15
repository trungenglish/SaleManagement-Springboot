package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
    long count();
    Supplier findByIdSupplier(int idSupplier);
}
