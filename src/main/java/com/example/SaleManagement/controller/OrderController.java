package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Order;
import com.example.SaleManagement.model.PurchaseOrder;
import com.example.SaleManagement.service.OrderService;
import com.example.SaleManagement.service.ProductService;
import com.example.SaleManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @GetMapping("/showInvoice")
    public String showOrder(Model model) {
        model.addAttribute("invoices",orderService.getListInvoice());
        return "/layouts/invoice/layout_list_invoice";
    }

    @GetMapping("/showDetail")
    public String detailPurchaseOrder(@RequestParam("orderId") int id, Model model) {
        model.addAttribute("order", orderService.getOrder(id));
        return "layouts/invoice/layout_detail_invoice";
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
