package com.example.SaleManagement.model;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DB_Employee")
public class Employee {
    @Id
    @Column(columnDefinition = "CHAR(12)")
    private String cccd;

    @Column(columnDefinition = "VARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(columnDefinition = "CHAR(10)")
    private String phone;

    @Column(columnDefinition = "VARCHAR(50)")
    private String position;
}
