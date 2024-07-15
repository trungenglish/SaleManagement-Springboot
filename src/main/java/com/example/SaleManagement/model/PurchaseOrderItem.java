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
@Table(name = "DB_PurchaseOrderItem")
public class PurchaseOrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPurchaseOrderItem;

    @Column(columnDefinition = "DECIMAL(19,2)")
    private double priceOrderPurchase;

    @Column(columnDefinition = "INTEGER")
    private int quantityItemOrderPurchase;

    @ManyToOne
    @JoinColumn(name = "idPurchaseOrder")
    private PurchaseOrder purchaseOrder;

    @ManyToOne
    @JoinColumn(name = "idPro")
    private Product product;
}
