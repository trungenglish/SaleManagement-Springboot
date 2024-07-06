package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;

public interface CategoriesRepository extends JpaRepository<Category, Integer>{
    Category findByIdCate(int idCate);
}
