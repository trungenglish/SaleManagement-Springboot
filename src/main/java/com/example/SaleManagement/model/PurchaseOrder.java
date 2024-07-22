package com.example.SaleManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DB_PurchaseOrder")
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPurchaseOrder;

    @Column(columnDefinition = "TIMESTAMP")
    private LocalDateTime dateOrderPurchase;

    @Column(columnDefinition = "DECIMAL(30,2)")
    private double totalPurchase;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> items = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "idSupplier")
    private Supplier supplier;

}
