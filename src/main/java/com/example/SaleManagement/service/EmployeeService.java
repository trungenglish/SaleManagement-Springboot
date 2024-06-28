package com.example.SaleManagement.service;

import com.example.SaleManagement.model.Employee;
import com.example.SaleManagement.repository.EmployeesRepository;
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

    public Employee addEmployee(Employee request){
        Employee employee = new Employee();

        employee.setCccd(request.getCccd());
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setPhone(request.getPhone());
        employee.setPosition(request.getPosition());
        return employeesRepository.save(employee);
    }

    public Employee getEmployee(String cccd){
        return employeesRepository.findByCccd(cccd);
    }

    public Employee updateEmployee(String cccd, Employee request){
        Employee employee = getEmployee(cccd);

        employee.setPhone(request.getPhone());
        employee.setEmail(request.getEmail());
        employee.setPosition(request.getPosition());
        employee.setName(request.getName());

        return employeesRepository.save(employee);
    }

    public void deleteEmployee(String cccd){
        Employee employee = getEmployee(cccd);
        employeesRepository.delete(employee);
    }
}
