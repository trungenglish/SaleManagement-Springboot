package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Category;
import com.example.SaleManagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("categories",categoryService.getListCategory());
        model.addAttribute("category",new Category());
        return "layouts/product/layout_list_category";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute("category") Category category){
        categoryService.addCategory(category);
        return "redirect:/categories/list";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("cateId") Category category,Model model){
        model.addAttribute("category",categoryService.getCategory(category.getIdCate()));
        return "layouts/product/layout_update_category";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute("category") Category category){
        categoryService.updateCategory(category.getIdCate(),category);
        return "redirect:/categories/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("cateId") int cccd){
        categoryService.deleteCategory(cccd);
        return "redirect:/categories/list";
    }
}
