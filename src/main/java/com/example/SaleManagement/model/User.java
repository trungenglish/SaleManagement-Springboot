package com.example.SaleManagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //ID tự tăng
    private Integer id;

    @Size(min = 5, max = 15,message = "USERNAME_INVALID")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "USERNAME_INVALID")
    private String username;
    @Size(min = 6, message = "Password must be at least 6 characters") //Kiểm tra độ dài của password
    private String password;
    private String fullname;
    private String email;
    @Size(min = 10, max = 10, message = "Phone number must be 10 characters")
    private String phone;
}
