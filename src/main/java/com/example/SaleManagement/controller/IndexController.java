package com.example.SaleManagement.controller;

import com.example.SaleManagement.service.EmployeeService;
import com.example.SaleManagement.service.ProductService;
import com.example.SaleManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @Autowired
    private ProductService productService;

    @Autowired
    public UserService userService;

    @Autowired
    public EmployeeService employeeService;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("countProducts", productService.countProducts());
        model.addAttribute("countUsers",userService.countUsers());
        model.addAttribute("countEmployees",employeeService.countEmployees());
        return "layouts/dashboard/index";
    }

}
