package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer>{

}
