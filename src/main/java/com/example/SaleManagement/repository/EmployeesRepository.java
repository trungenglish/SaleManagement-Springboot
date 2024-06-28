package com.example.SaleManagement.repository;

import com.example.SaleManagement.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeesRepository extends JpaRepository<Employee, String>{
    Employee findByCccd(String cccd);
    boolean existsByCccd(String cccd);
}
