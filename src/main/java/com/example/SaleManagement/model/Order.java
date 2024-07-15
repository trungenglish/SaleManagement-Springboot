package com.example.SaleManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DB_Order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOrder;

    @Column(columnDefinition = "DATETIME")
    private LocalDateTime dateOrder;

    @Column(columnDefinition = "DECIMAL(19,4)")
    private double total;

    @Column(columnDefinition = "NVARCHAR(50)")
    private  String status;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
}
