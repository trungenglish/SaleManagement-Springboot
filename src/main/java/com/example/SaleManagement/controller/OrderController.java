package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Order;
import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.service.ProductService;
import com.example.SaleManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @GetMapping("/showInvoice")
    public String showOrder(Model model) {
        Order order = new Order();
        order.setDateOrder(LocalDateTime.now());
        model.addAttribute("order",order);
        model.addAttribute("products",productService.getListProduct());
        model.addAttribute("users",userService.getListUser());
        return "/layouts/invoice/layouts_list_invoice";
    }

    @GetMapping("/showFormOrder")
    public String showFormOrder(Model model) {
        Order order = new Order();
        order.setDateOrder(LocalDateTime.now());
        model.addAttribute("order",order);
        model.addAttribute("products",productService.getListProduct());
        model.addAttribute("users",userService.getListUser());
        return "/layouts/invoice/layout_order";
    }

}
