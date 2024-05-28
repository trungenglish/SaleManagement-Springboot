package com.example.SaleManagement.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//thư viện lombook giúp tự động sinh ra các phương thức getter, setter, constructor, toString
@Getter
@Setter
//đánh dấu bảng Product là 1 entity 1 đối tượng sẽ được ánh xạ tới 1 bảng trong csdl
@Entity
@Table(name = "products")
public class Product {
    //đánh dấu id là khóa chính
    @Id
    //id tự tăng
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String brand;
    private String category;
    private double price;

    //đánh dấu cột này có giá trị là text vì các trường String JPA định nghĩa là Varchar(255)
    @Column(columnDefinition = "TEXT")
    private String description;
    private Date createdAt;
    private String imageFileName;

}
