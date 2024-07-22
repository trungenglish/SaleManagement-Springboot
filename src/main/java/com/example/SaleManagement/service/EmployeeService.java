package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Employee;
import com.example.SaleManagement.repository.EmployeesRepository;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeesRepository employeesRepository ;

    public List<Employee> getListEmployee(){
        return employeesRepository.findAll();
    }

    public boolean existsByCccd(String cccd) {
        return employeesRepository.existsByCccd(cccd);
    }

    public long countEmployees(){
        return employeesRepository.count();
    }

    public void addEmployee(Employee request){
        Employee employee = new Employee();

        employee.setCccd(request.getCccd());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setPosition(request.getPosition());
        employeesRepository.save(employee);
    }

    public Employee getEmployee(String cccd){
        return employeesRepository.findByCccd(cccd);
    }

    public void updateEmployee(String cccd, Employee request){
        Employee employee = getEmployee(cccd);

        employee.setPhone(request.getPhone());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setName(request.getName());

        employeesRepository.save(employee);
    }

    public void deleteEmployee(String cccd){
        Employee employee = getEmployee(cccd);
        employeesRepository.delete(employee);
    }

    public void generateExcel(HttpServletResponse response) throws Exception {
        // code to generate excel
        List<Employee> employees = employeesRepository.findAll();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("User Info");
        HSSFRow row = sheet.createRow(0);

        row.createCell(0).setCellValue("CCCD");
        row.createCell(1).setCellValue("Name");
        row.createCell(2).setCellValue("Email");
        row.createCell(3).setCellValue("Phone");
        row.createCell(4).setCellValue("Position");

        int dataRowIndex = 1;

        for(Employee employee: employees) {
            HSSFRow dataRow = sheet.createRow(dataRowIndex);
            dataRow.createCell(0).setCellValue(employee.getCccd());
            dataRow.createCell(1).setCellValue(employee.getName());
            dataRow.createCell(2).setCellValue(employee.getEmail());
            dataRow.createCell(3).setCellValue(employee.getPhone());
            dataRow.createCell(4).setCellValue(employee.getPosition());

            dataRowIndex++;
        }

        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();

    }
}
