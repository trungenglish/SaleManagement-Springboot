package com.example.SaleManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    private String cccd;

    private String name;
    private String email;

    private String phone;
    private String position;
}
