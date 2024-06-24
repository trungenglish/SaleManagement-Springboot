package com.example.SaleManagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class TestController {
    @GetMapping("/login")
    public String login(){
        return "login";
    }

//    @GetMapping("/")
//    public String home() {
//        return "/layout/user/list_user";
//    }
}
