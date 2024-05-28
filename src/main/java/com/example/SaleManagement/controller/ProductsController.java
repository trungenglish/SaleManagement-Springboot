package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Product;
import com.example.SaleManagement.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductsRepository repository;

    @GetMapping({"", "/"})
    public String showProductsList(Model model) {
        List<Product> products = repository.findAll();
        model.addAttribute("products", products);
        return "products";
    }
}
