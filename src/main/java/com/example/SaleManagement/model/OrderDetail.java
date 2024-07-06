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
@Table(name = "DB_OrderDetail")
public class OrderDetail {
    @Id
    private int idOrderDetail;

    @Column(columnDefinition = "INTEGER")
    private int quantity;

    @Column(columnDefinition = "DECIMAL(10,2)")
    private double price;

    @ManyToOne
    @JoinColumn(name = "idOrder")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "idPro")
    private Product product;
}
