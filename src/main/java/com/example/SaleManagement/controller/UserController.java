package com.example.SaleManagement.controller;


import com.example.SaleManagement.model.User;
import com.example.SaleManagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/users")
public class UserController {
     @Autowired
     private UserService userService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("users",userService.getListUser());
        model.addAttribute("user",new User());
        return "layouts/user/layout_list_user";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("userId") int id, Model model){
        model.addAttribute("user",userService.getUser(id));
        return "layouts/user/layout_update_user";
    }

    @GetMapping("/checkUsername")
    @ResponseBody
    public boolean checkUsername(@RequestParam String username) {
        return userService.existsByUsername(username);
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") User user){
        userService.addUser(user);
        return "redirect:/users/list";
    }

    @PostMapping("/update")
        public String updateUser(@ModelAttribute("user") User user){
        userService.updateUser(user.getIdUser(),user);
        return "redirect:/users/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("userId") int id){
        userService.deleteUser(id);
        return "redirect:/users/list";
    }
}
