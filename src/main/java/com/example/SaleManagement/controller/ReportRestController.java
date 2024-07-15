package com.example.SaleManagement.controller;

import com.example.SaleManagement.service.*;
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
    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;
    @Autowired
    private ImportGoodsService importGoodsService;

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

    @GetMapping("/excel-product")
    public void generateExcel_Pro(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product.xls";

        response.setHeader(headerKey, headerValue);

        productService.generateExcel(response);
    }

    @GetMapping("/excel-supplier")
    public void generateExcel_Sup(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product.xls";

        response.setHeader(headerKey, headerValue);

        supplierService.generateExcel(response);
    }

    @GetMapping("/excel-listImportGoods")
    public void generateExcel_listImportGoods(HttpServletResponse response) throws Exception{

        response.setContentType("application/octet-stream");

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=product.xls";

        response.setHeader(headerKey, headerValue);

        supplierService.generateExcel(response);
    }
}
