package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Category;
import com.example.SaleManagement.repository.CategoriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoriesRepository categoryRepository;

    public List<Category> getListCategory(){
        return categoryRepository.findAll();
    }

    public Category getCategory(int idCate){
        return categoryRepository.findByIdCate(idCate);
    }

    public Category addCategory(Category request){
        Category category = new Category();

        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public Category updateCategory(int idCate, Category request){
        Category category = getCategory(idCate);

        category.setName(request.getName());
        return categoryRepository.save(category);
    }

    public void deleteCategory(int idCate){
        categoryRepository.deleteById(idCate);
    }
}
