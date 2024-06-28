package com.example.SaleManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ID tự tăng
    private Integer id;

    private String username;

    private String password;
    private String name;
    private String email;
    private String phone;

}
