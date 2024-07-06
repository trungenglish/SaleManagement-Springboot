package com.example.SaleManagement.controller;

import com.example.SaleManagement.model.Employee;
import com.example.SaleManagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/list")
    public String list(Model model){
        model.addAttribute("employees",employeeService.getListEmployee());
        model.addAttribute("employee",new Employee());
        return  "layouts/employee/layout_list_employee";
    }

    @GetMapping("/formUpdate")
    public String showFormUpdate(@RequestParam("employeeCccd") String cccd, Model model){
        model.addAttribute("employee",employeeService.getEmployee(cccd));
        return "layouts/employee/layout_update_employee";
    }

    @GetMapping("/checkCccd")
    @ResponseBody
    public boolean checkCccd(@RequestParam String cccd) {
        return employeeService.existsByCccd(cccd);
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("employee") Employee employee){
        employeeService.addEmployee(employee);
        return "redirect:/employees/list";
    }

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("employee") Employee employee){
        employeeService.updateEmployee(employee.getCccd(),employee);
        return "redirect:/users/list";
    }

    @GetMapping("/delete")
    public String deleteUser(@RequestParam("employeeCccd") String cccd){
        employeeService.deleteEmployee(cccd);
        return "redirect:/employees/list";
    }
}
