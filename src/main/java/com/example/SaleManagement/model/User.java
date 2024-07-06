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
@Table(name = "DB_User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ID tự tăng
    private Integer idUser;

    @Column(columnDefinition = "VARCHAR(50)")
    private String username;

    @Column(columnDefinition = "VARCHAR(225)")
    private String password;

    @Column(columnDefinition = "NVARCHAR(50)")
    private String name;

    @Column(columnDefinition = "VARCHAR(50)")
    private String email;

    @Column(columnDefinition = "CHAR(10)")
    private String phone;

    @Column(columnDefinition = "NVARCHAR(100)")
    private String address;
}
