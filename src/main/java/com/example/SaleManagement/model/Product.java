package com.example.SaleManagement.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "DB_Product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPro;

    @Column(columnDefinition = "VARCHAR(50)")
    private String namePro;

    @Column(columnDefinition = "DECIMAL(19,2)")
    private double price;

    @Column(columnDefinition = "VARCHAR(225)")
    private String description;

    @Lob
    private Blob imageFile;

    @ManyToOne
    @JoinColumn(name = "idCate")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderDetail> orderDetails;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<PurchaseOrderItem> purchaseOrderItems;

    @OneToOne(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Inventory inventory;
}
