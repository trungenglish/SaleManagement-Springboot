package com.example.SaleManagement.controller;

import com.example.SaleManagement.service.EmployeeService;
import com.example.SaleManagement.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/excel-user")
    public void generateExcel_User(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users.xls";

        response.setHeader(headerKey, headerValue);

        userService.generateExcel(response);
    }

    @GetMapping("/excel-employee")
    public void generateExcel_Cate(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=employee.xls";

        response.setHeader(headerKey, headerValue);

        employeeService.generateExcel(response);
    }
}
