package com.example.SaleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class IndexController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/index")
    public String index() {
        return "layouts/dashboard/index";
    }
}
