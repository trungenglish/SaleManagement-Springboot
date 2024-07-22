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
@Table(name = "DB_Inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInventory;

    @OneToOne
    @JoinColumn(name = "idPro")
    private Product product;

    @Column(columnDefinition = "INTEGER")
    private int quantity;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime lastUpdated;
}
