package com.example.SaleManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

//thư viện lombook giúp tự động sinh ra các phương thức getter, setter, constructor, toString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DB_Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //id tự tăng
    private int idPro;

    @Column(columnDefinition = "VARCHAR(50)")
    private String namePro;

    private int quantity;

    @Column(columnDefinition = "DECIMAL(19,2)")
    private double price;

    //đánh dấu cột này có giá trị là text vì các trường String JPA định nghĩa là Varchar(255)
    @Column(columnDefinition = "NVARCHAR(225)")
    private String description;

    @ManyToOne
    @JoinColumn(name = "idCate")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "idSupplier")
    private Supplier supplier;
}
